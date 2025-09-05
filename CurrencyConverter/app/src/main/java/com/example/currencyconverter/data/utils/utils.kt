package com.example.currencyconverter.data.utils

import android.widget.TimePicker
import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


fun <T> safeCallApi(callApi:suspend ()->T): Flow<ApiResult<T>> {
    return flow{
        try {
            emit(ApiResult.Loading)
            val response = callApi.invoke()
            emit(ApiResult.Success(response))
        }
        catch (exception:Exception){
            emit(ApiResult.Error(exception))
        }
    }
}

fun getSerializedNames(clazz: Class<*>): List<String> {
    return clazz.declaredFields.mapNotNull { field ->
        field.getAnnotation(SerializedName::class.java)?.value
    }
}