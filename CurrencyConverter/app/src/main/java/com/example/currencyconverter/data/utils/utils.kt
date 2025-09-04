package com.example.currencyconverter.data.utils

import android.widget.TimePicker
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


fun <T> safeCallApi(callApi:suspend ()->T): Flow<ApiResult<T>> {
    return flow{
        try {
            ApiResult.Loading
            val response = callApi.invoke()
            ApiResult.Success(response)
        }
        catch (exception:Exception){
            ApiResult.Error(exception)
        }
    }
}