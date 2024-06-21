package com.example.cryptoapp.presentation.view

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cryptoapp.data.remote_source.ApiFactory
import com.example.cryptoapp.data.local_source.AppDatabase
import com.example.cryptoapp.data.repository.CryptoRepositoryImpl
import com.example.cryptoapp.domain.model.CoinInfo
import com.example.cryptoapp.domain.usecases.GetPriceInfoCoinByIdUseCase
import com.example.cryptoapp.domain.usecases.GetPriceListUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class CoinViewModel(application: Application, private val fSym: String) : ViewModel() {

    private val db = AppDatabase.getInstance(application)
    private val repository = CryptoRepositoryImpl(ApiFactory.apiService,db.coinInfoDao())

    private val getPriceInfoCoinByIdUseCase = GetPriceInfoCoinByIdUseCase(repository)
    private val getPriceListUseCase = GetPriceListUseCase(repository)
    private var _coinPriceList = MutableLiveData<List<CoinInfo>>()
    val coinPriceList: LiveData<List<CoinInfo>> = _coinPriceList

    private var _coinPriceInfo = MutableLiveData<CoinInfo>()
    val coinPriceInfo: LiveData<CoinInfo> = _coinPriceInfo

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    init {
        loadData()
        getDetailCoin()
    }

    private fun getDetailCoin() {
        coroutineScope.launch {
            _coinPriceInfo.postValue(getPriceInfoCoinByIdUseCase(fSym))
        }
    }

    private fun loadData() {
        coroutineScope.launch {
            _coinPriceList.postValue(getPriceListUseCase())
        }
    }

    override fun onCleared() {
        super.onCleared()
        coroutineScope.cancel()
    }
}