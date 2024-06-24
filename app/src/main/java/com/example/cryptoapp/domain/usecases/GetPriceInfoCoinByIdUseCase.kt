package com.example.cryptoapp.domain.usecases

import androidx.lifecycle.LiveData
import com.example.cryptoapp.domain.model.CoinInfo
import com.example.cryptoapp.domain.repository.CryptoRepository

class GetPriceInfoCoinByIdUseCase(private val repository : CryptoRepository) {

     operator fun invoke(fSym: String): LiveData<CoinInfo> {
       return repository.getCoinById(fSym)
    }

}