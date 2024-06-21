package com.example.cryptoapp.domain.repository

import androidx.lifecycle.LiveData
import com.example.cryptoapp.domain.model.CoinInfo

interface CryptoRepository {

    fun getCoinInfoList(): LiveData<List<CoinInfo>>

    fun getCoinById(fromSym: String): LiveData<CoinInfo>

    suspend fun loadData()

}