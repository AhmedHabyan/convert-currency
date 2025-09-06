package com.example.currencyconverter.ui.viewmodel


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyconverter.data.usecase.ConverterUseCase
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
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch

@HiltViewModel
class ActivityViewModel @Inject constructor(
    private val converterUseCase: ConverterUseCase
):ViewModel() {

    private var _uiState = MutableStateFlow<UiState>(UiState.Ideal)
    val uiState = _uiState

    private var _converterUiState = MutableStateFlow<UiState>(UiState.Ideal)
    val converterUiState = _converterUiState

    private var _historyUiState = MutableStateFlow<UiState>(UiState.Ideal)
    val historyUiState = _historyUiState


    fun getAllCurrencies(){
        viewModelScope.launch(Dispatchers.IO) {
            converterUseCase.getAllCurrencies().collect {apiResult->
                when(apiResult){
                    is ApiResult.Error -> {
                        Log.e("get","error")
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
            converterUseCase.getCurrencyConversion(
                base, symbol
            ).collect {
                when(it){
                    is ApiResult.Error -> {
                        converterUiState.emit(UiState.Error(it.error))
                    }
                    is ApiResult.Loading -> {
                        converterUiState.emit(UiState.Loading)
                    }
                    is ApiResult.Success -> {
                        calculateConversion(it.response,amount)
                    }
                }
            }
        }

    }
    fun calculateConversion(currencyConversionDto: CurrencyConversionDto,amount:String) {
        viewModelScope.launch {
            currencyConversionDto.conversionAmount?.let {
                val result = amount.toDouble() * it
                converterUiState.emit(UiState.Success(result))
            }
        }
    }


    fun insertTransaction(transactionDto: TransactionDto){
        viewModelScope.launch(Dispatchers.IO) {
            converterUseCase.insertTransaction(transactionDto)
        }
    }

    fun getAllTransactions(){
        viewModelScope.launch(Dispatchers.IO) {
            converterUseCase.getAllTransactions().collect{
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