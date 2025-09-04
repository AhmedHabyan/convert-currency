package com.example.currencyconverter.data.utils

sealed class ApiResult<out T> {

    object Loading:ApiResult<Nothing>()
    data class Success<E>(val response:E):ApiResult<E>()
    data class Error(val error:Exception):ApiResult<Nothing>()
}