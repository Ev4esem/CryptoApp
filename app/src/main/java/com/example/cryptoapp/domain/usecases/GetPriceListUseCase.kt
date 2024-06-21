package com.example.cryptoapp.domain.usecases

import com.example.cryptoapp.domain.model.CoinInfo
import com.example.cryptoapp.domain.repository.CryptoRepository

class GetPriceListUseCase(private val repository: CryptoRepository) {

    suspend operator fun invoke() : List<CoinInfo> {
        return repository.getCoinInfoList()
    }

}