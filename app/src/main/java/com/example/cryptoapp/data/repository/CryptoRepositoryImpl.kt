package com.example.cryptoapp.data.repository

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.cryptoapp.data.local_source.AppDatabase
import com.example.cryptoapp.data.mapper.toCoinInfo
import com.example.cryptoapp.data.mapper.toCoinInfoDb
import com.example.cryptoapp.data.mapper.toJsonContainerToListCoinInfo
import com.example.cryptoapp.data.mapper.toNamesListToString
import com.example.cryptoapp.data.remote_source.ApiFactory
import com.example.cryptoapp.data.remote_source.ApiService
import com.example.cryptoapp.domain.model.CoinInfo
import com.example.cryptoapp.domain.repository.CryptoRepository
import kotlinx.coroutines.delay

class CryptoRepositoryImpl(
    application : Application
) : CryptoRepository {

    private val coinInfoDao = AppDatabase.getInstance(application).coinInfoDao()
    private val apiService : ApiService = ApiFactory.apiService


    override fun getCoinInfoList() : LiveData<List<CoinInfo>> {
        return coinInfoDao.getPriceList().map { listCoinInfoDb ->
            listCoinInfoDb.map { coinInfoDb ->
                coinInfoDb.toCoinInfo()
            }
        }
    }

    override fun getCoinById(fromSym : String) : LiveData<CoinInfo> {
        return coinInfoDao.getPriceInfoCoinById(fromSym).map {
            it.toCoinInfo()
        }
    }

    @SuppressLint("LongLogTag")
    override suspend fun loadData() {
        while (true) {
            try {
                val topCoins = apiService.getTopCoinsInfo(limit = 50)
                val fSyms = topCoins.toNamesListToString()
                val jsonContainer = apiService.getFullPriceList(fSyms = fSyms)
                val coinInfoDtoList = jsonContainer.toJsonContainerToListCoinInfo()
                val dbModelList = coinInfoDtoList.map { it.toCoinInfoDb() }
                coinInfoDao.insertPriceList(dbModelList)
            } catch (e : Exception) {
                Log.e("ErrorCryptoRepositoryImpl", e.toString())
            }
            delay(10000)
        }
    }

}