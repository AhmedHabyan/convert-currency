package com.example.currencyconverter.data.model

import com.example.currencyconverter.domain.model.CurrencyDto
import com.google.gson.annotations.SerializedName

data class CurrencyResponse(

	@field:SerializedName("success")
	val success: Boolean? = null,

	@field:SerializedName("symbols")
	val symbols: Symbols? = null
) {
	fun toCurrentDto(): CurrencyDto {
		return CurrencyDto(
			currencies = listOf
				(
				symbols?.fJD,
				symbols?.mXN,
				symbols?.sTD,
				symbols?.lVL,
				symbols?.sCR,
				symbols?.cDF,
				symbols?.bBD,
				symbols?.gTQ,
				symbols?.cLP,
				symbols?.hNL,
				symbols?.uGX,
				symbols?.zAR,
				symbols?.tND,
				symbols?.sTN,
				symbols?.sLE,
				symbols?.cUC,
				symbols?.bSD,
				symbols?.sLL,
				symbols?.sDG,
				symbols?.iQD,
				symbols?.cUP,
				symbols?.gMD,
				symbols?.tWD,
				symbols?.rSD,
				symbols?.dOP,
				symbols?.kMF,
				symbols?.mYR,
				symbols?.fKP,
				symbols?.xOF,
				symbols?.gEL,
				symbols?.bTC,
				symbols?.uYU,
				symbols?.mAD,
				symbols?.cVE,
				symbols?.tOP,
				symbols?.aZN,
				symbols?.oMR,
				symbols?.pGK,
				symbols?.kES,
				symbols?.sEK,
				symbols?.cNH,
				symbols?.bTN,
				symbols?.uAH,
				symbols?.gNF,
				symbols?.eRN,
				symbols?.mZN,
				symbols?.sVC,
				symbols?.aRS,
				symbols?.qAR,
				symbols?.iRR,
				symbols?.cNY,
				symbols?.tHB,
				symbols?.uZS,
				symbols?.xPF,
				symbols?.mRU,
				symbols?.bDT,
				symbols?.lYD,
				symbols?.bMD,
				symbols?.kWD,
				symbols?.pHP,
				symbols?.rUB,

				)
		)

	}
}