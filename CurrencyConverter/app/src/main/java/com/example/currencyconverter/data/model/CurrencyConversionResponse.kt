package com.example.currencyconverter.data.model

import com.example.currencyconverter.domain.model.CurrencyConversionDto
import com.google.gson.annotations.SerializedName

data class CurrencyConversionResponse(

	@field:SerializedName("date")
	val date: String? = null,

	@field:SerializedName("success")
	val success: Boolean? = null,

	@field:SerializedName("rates")
	val rates: Map<String,Double>? = null,

	@field:SerializedName("timestamp")
	val timestamp: Int? = null,

	@field:SerializedName("base")
	val base: String? = null
){
	fun toCurrencyConversionDto():CurrencyConversionDto{

		return CurrencyConversionDto(
			conversionAmount = rates?.values?.first()
		)
	}
}