package com.example.cryptoapp.domain.model

data class CoinInfo(
    val price: String?,
    val lowDay: String?,
    val highDay: String?,
    val lastMarket: String?,
    val lastUpdate: String?,
    val fromSymbol: String?,
    val toSymbol: String?,
    val imageUrl: String,
)
