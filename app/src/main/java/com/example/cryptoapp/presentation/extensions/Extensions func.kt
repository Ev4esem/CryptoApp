package com.example.cryptoapp.presentation.extensions

import com.example.cryptoapp.presentation.adapters.CoinInfoAdapter
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

fun Long?.toConvertTimestampToTime() : String {
    if (this == null) return UNKNOWN_TIME_STAMP
    val stamp = Timestamp(this * MILLIS_IN_SECOND)
    val date = Date(stamp.time)
    val pattern = "HH:mm:ss"
    val sdf = SimpleDateFormat(pattern, Locale.getDefault())
    sdf.timeZone = TimeZone.getDefault()
    return sdf.format(date)
}

fun String?.toGetFullImageUrl(): String {
    return BASE_IMAGE_URL + this
}

private const val BASE_IMAGE_URL = "https://cryptocompare.com"
private const val UNKNOWN_TIME_STAMP = ""
private const val MILLIS_IN_SECOND = 1000
