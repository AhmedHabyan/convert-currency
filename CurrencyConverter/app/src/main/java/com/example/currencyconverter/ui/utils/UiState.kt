package com.example.currencyconverter.ui.utils

sealed class UiState {
    object Loading : UiState()
    object Ideal : UiState()
    data class Success<T>(val response: T) : UiState()
    data class Error(val exception: Exception) : UiState()
}