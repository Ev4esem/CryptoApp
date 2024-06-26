package com.example.cryptoapp.domain.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CoinNameContainer (
    @SerializedName("CoinInfo")
    @Expose
    val coinInfo: CoinName? = null
)
