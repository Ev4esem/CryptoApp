package com.example.cryptoapp.data.mapper

import com.example.cryptoapp.data.local_source.model.CoinInfoDb
import com.example.cryptoapp.data.remote_source.model.CoinInfoDto
import com.example.cryptoapp.data.remote_source.model.CoinInfoJsonContainerDto
import com.example.cryptoapp.data.remote_source.model.CoinNamesListDto
import com.example.cryptoapp.domain.model.CoinInfo
import com.google.gson.Gson

fun CoinInfoDb.toCoinInfo() = CoinInfo(
    fromSymbol = fromSymbol,
    toSymbol = toSymbol,
    price = price,
    lastUpdate = lastUpdate,
    highDay = highDay,
    lowDay = lowDay,
    lastMarket = lastMarket,
    imageUrl = imageUrl
)

fun CoinInfoDto.toCoinInfoDb() = CoinInfoDb(
    fromSymbol = fromSymbol,
    toSymbol = toSymbol,
    price = price,
    lastUpdate = lastUpdate,
    highDay = highDay,
    lowDay = lowDay,
    lastMarket = lastMarket,
    imageUrl = imageUrl
)

fun CoinInfoJsonContainerDto.toJsonContainerToListCoinInfo(): List<CoinInfoDto> {
    val result = mutableListOf<CoinInfoDto>()
    val jsonObject = this.coinPriceInfoJsonObject ?: return result
    val coinKeySet = jsonObject.keySet()
    for (coinKey in coinKeySet) {
        val currencyJson = jsonObject.getAsJsonObject(coinKey)
        val currencyKeySet = currencyJson.keySet()
        for (currencyKey in currencyKeySet) {
            val priceInfo = Gson().fromJson(
                currencyJson.getAsJsonObject(currencyKey),
                CoinInfoDto::class.java
            )
            result.add(priceInfo)
        }
    }
    return result
}

fun CoinNamesListDto.toNamesListToString(): String {
   return this.data?.map {
        it.coinInfo?.name
    }?.joinToString(",") ?: ""
}
