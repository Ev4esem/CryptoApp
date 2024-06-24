package com.example.cryptoapp.data.local_source.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "full_price_list")
data class CoinInfoDb(
    @PrimaryKey val fromSymbol: String,
    val price: String?,
    val lowDay: String?,
    val highDay: String?,
    val lastMarket: String?,
    val lastUpdate: Long?,
    val toSymbol: String?,
    val imageUrl: String
)


