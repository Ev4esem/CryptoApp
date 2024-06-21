package com.example.cryptoapp.domain.usecases

import com.example.cryptoapp.domain.model.CoinInfo
import com.example.cryptoapp.domain.repository.CryptoRepository

class GetPriceInfoCoinByIdUseCase(private val repository : CryptoRepository) {

    suspend operator fun invoke(fSym: String): CoinInfo {
       return repository.getCoinById(fSym)
    }

}