package com.example.cryptoapp.presentation.view

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptoapp.data.repository.CryptoRepositoryImpl
import com.example.cryptoapp.domain.usecases.GetPriceInfoCoinByIdUseCase
import com.example.cryptoapp.domain.usecases.GetPriceListUseCase
import com.example.cryptoapp.domain.usecases.LoadDataUseCase
import kotlinx.coroutines.launch

class MainViewModel(application : Application): AndroidViewModel(application) {

    private val repository = CryptoRepositoryImpl(application)

    private val getCoinInfoListUseCase = GetPriceListUseCase(repository)
    private val getCoinInfoUseCase = GetPriceInfoCoinByIdUseCase(repository)
    private val loadDataUseCase = LoadDataUseCase(repository)

    val coinInfoList = getCoinInfoListUseCase()

    fun getDetailInfo(fSym: String) = getCoinInfoUseCase(fSym)

    init {
        viewModelScope.launch {
            loadDataUseCase()
        }
    }

}