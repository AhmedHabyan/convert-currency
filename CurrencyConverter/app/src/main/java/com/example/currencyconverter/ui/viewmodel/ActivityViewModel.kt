package com.example.currencyconverter.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyconverter.data.utils.ApiResult
import com.example.currencyconverter.domain.contract.repo.Repo
import com.example.currencyconverter.domain.model.CurrencyDto
import com.example.currencyconverter.ui.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class ActivityViewModel @Inject constructor(
    private val repo:Repo
):ViewModel() {

    private var _uiState = MutableStateFlow<UiState<Any>>(UiState.Ideal)
    val uiState = _uiState

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
}