package com.example.currencyconverter.data.model

import android.util.Log
import com.example.currencyconverter.data.utils.getSerializedNames
import com.example.currencyconverter.domain.model.CurrencyDto
import com.google.gson.annotations.SerializedName

data class CurrencyResponse(

	@field:SerializedName("success")
	val success: Boolean? = null,

	@field:SerializedName("symbols")
	val symbols: Symbols? = null
) {
	fun toCurrentDto(): CurrencyDto {
		val currencyAbbreviations = getSerializedNames(Symbols::class.java)

		return CurrencyDto(
			currencies = currencyAbbreviations
		)

	}
}