package com.example.currencyconverter.ui.utils

sealed class UiState<out T> {
    object Loading : UiState<Nothing>()
    object Ideal : UiState<Nothing>()
    data class Success<out T>(val response: T) : UiState<T>()
    data class Error(val exception: Exception) : UiState<Nothing>()
}