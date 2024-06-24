package com.example.cryptoapp.domain.usecases

import androidx.lifecycle.LiveData
import com.example.cryptoapp.domain.model.CoinInfo
import com.example.cryptoapp.domain.repository.CryptoRepository

class GetPriceListUseCase(private val repository: CryptoRepository) {

    operator fun invoke() : LiveData<List<CoinInfo>> {
        return repository.getCoinInfoList()
    }

}