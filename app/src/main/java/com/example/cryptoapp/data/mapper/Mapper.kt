package com.example.cryptoapp.data.mapper

import com.example.cryptoapp.data.local_source.model.CoinInfoDb
import com.example.cryptoapp.data.remote_source.model.CoinInfoDto
import com.example.cryptoapp.data.remote_source.model.CoinInfoJsonContainerDto
import com.example.cryptoapp.data.remote_source.model.CoinNamesListDto
import com.example.cryptoapp.domain.model.CoinInfo
import com.google.gson.Gson
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

fun CoinInfoDb.toCoinInfo() = CoinInfo(
    fromSymbol = fromSymbol,
    toSymbol = toSymbol,
    price = price,
    lastUpdate = lastUpdate.toConvertTimestampToTime(),
    highDay = highDay,
    lowDay = lowDay,
    lastMarket = lastMarket,
    imageUrl = BASE_IMAGE_URL + imageUrl
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

fun Long?.toConvertTimestampToTime() : String {
    if (this == null) return ""
    val stamp = Timestamp(this * 1000)
    val date = Date(stamp.time)
    val pattern = "HH:mm:ss"
    val sdf = SimpleDateFormat(pattern, Locale.getDefault())
    sdf.timeZone = TimeZone.getDefault()
    return sdf.format(date)
}


fun CoinNamesListDto.toNamesListToString(): String {
   return this.data?.map {
        it.coinInfo?.name
    }?.joinToString(",") ?: ""
}

const val BASE_IMAGE_URL = "https://cryptocompare.com"

