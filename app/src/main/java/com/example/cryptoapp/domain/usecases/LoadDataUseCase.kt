package com.example.cryptoapp.domain.usecases

import com.example.cryptoapp.domain.repository.CryptoRepository

class LoadDataUseCase(private val repository : CryptoRepository) {

    suspend operator fun invoke() {
        return repository.loadData()
    }

}