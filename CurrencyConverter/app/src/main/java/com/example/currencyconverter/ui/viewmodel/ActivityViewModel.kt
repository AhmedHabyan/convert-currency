package com.example.currencyconverter.ui.viewmodel


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyconverter.data.utils.ApiResult
import com.example.currencyconverter.domain.contract.repo.Repo
import com.example.currencyconverter.domain.model.CurrencyConversionDto
import com.example.currencyconverter.domain.model.TransactionDto
import com.example.currencyconverter.ui.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch

@HiltViewModel
class ActivityViewModel @Inject constructor(
    private val repo:Repo
):ViewModel() {

    private var _uiState = MutableStateFlow<UiState>(UiState.Ideal)
    val uiState = _uiState

    private var _converterUiState = MutableStateFlow<UiState>(UiState.Ideal)
    val converterUiState = _converterUiState

    private var _historyUiState = MutableStateFlow<UiState>(UiState.Ideal)
    val historyUiState = _historyUiState


    fun getAllCurrencies(){
        viewModelScope.launch(Dispatchers.IO) {
            repo.getAllCurrencies().collect {apiResult->
                when(apiResult){
                    is ApiResult.Error -> {
                        _uiState.value= UiState.Error(apiResult.error)
                    }
                    ApiResult.Loading ->{
                        _uiState.value = UiState.Loading
                    }
                    is ApiResult.Success -> {
                        _uiState.value = UiState.Success(apiResult.response)
                    }
                }
            }
        }
    }
    var job:Job?=null
    fun calculateAmountConversion(base:String, symbol:String, amount:String){

        job?.cancel()
        job = viewModelScope.launch(Dispatchers.IO) {
            delay(600)
            repo.getCurrencyConversion(
                base, symbol
            ).collect {
                when(it){
                    is ApiResult.Error -> {
                        converterUiState.value = UiState.Error(it.error)
                    }
                    is ApiResult.Loading -> {
                        converterUiState.value = UiState.Loading
                    }
                    is ApiResult.Success -> {
                        calculateConversion(it.response,amount)
                    }
                }
            }
        }

    }
    fun calculateConversion(currencyConversionDto: CurrencyConversionDto,amount:String) {
        currencyConversionDto.conversionAmount?.let {
            val result = amount.toDouble() * it
            converterUiState.value = UiState.Success(result)
        }
    }


    fun insertTransaction(transactionDto: TransactionDto){
        viewModelScope.launch(Dispatchers.IO) {
            repo.insertTransaction(transactionDto)
        }
    }

    fun getAllTransactions(){
        viewModelScope.launch(Dispatchers.IO) {
            repo.getAllTransactions().collect{
                when(it){
                    is ApiResult.Error -> {
                        historyUiState.value= UiState.Error(it.error)
                    }
                    is ApiResult.Loading -> {
                        historyUiState.value= UiState.Loading
                    }
                    is ApiResult.Success -> {
                        historyUiState.value= UiState.Success(it.response)
                    }
                }
            }
        }
    }

}