package com.example.cryptoapp.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
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
    private val application : Application
) : CryptoRepository {

    private val coinInfoDao = AppDatabase.getInstance(application).coinInfoDao()
    private val apiService : ApiService = ApiFactory.apiService


    override fun getCoinInfoList() : LiveData<List<CoinInfo>> {
        return Transformations.map(coinInfoDao.getPriceList()) { listCoinInfoDb ->
            listCoinInfoDb.map { coinInfoDb ->
                coinInfoDb.toCoinInfo()
            }
        }
    }

    override fun getCoinById(fromSym : String) : LiveData<CoinInfo> {
        return Transformations.map(coinInfoDao.getPriceInfoCoinById(fromSym)) {
            it.toCoinInfo()
        }
    }

    override suspend fun loadData() {
        while (true) {
            val topCoins = apiService.getTopCoinsInfo(limit = 50)
            val fSyms = topCoins.toNamesListToString()
            val jsonContainer = apiService.getFullPriceList(fSyms = fSyms)
            val coinInfoDtoList = jsonContainer.toJsonContainerToListCoinInfo()
            val dbModelList = coinInfoDtoList.map { it.toCoinInfoDb() }
            coinInfoDao.insertPriceList(dbModelList)
            delay(10000)
        }
    }

}