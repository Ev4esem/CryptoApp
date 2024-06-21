package com.example.cryptoapp.data.remote_source.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CoinNameContainerDto(
    @SerializedName("CoinInfo")
    @Expose
    val coinInfo : CoinNameDto? = null
)



