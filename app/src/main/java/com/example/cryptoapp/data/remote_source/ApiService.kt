package com.example.cryptoapp.data.remote_source

import com.example.cryptoapp.data.remote_source.model.CoinInfoJsonContainerDto
import com.example.cryptoapp.data.remote_source.model.CoinNamesListDto
import com.example.cryptoapp.domain.model.CoinPriceInfoRawData
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("top/totalvolfull")
    suspend fun getTopCoinsInfo(
        @Query(QUERY_PARAM_API_KEY) apiKey: String = UNKNOWN_API_KEY,
        @Query(QUERY_PARAM_LIMIT) limit: Int = LIMIT_LOAD_CURRENCY,
        @Query(QUERY_PARAM_TO_SYMBOL) tSym: String = CURRENCY
    ): CoinNamesListDto

    @GET("pricemultifull")
    suspend fun getFullPriceList(
        @Query(QUERY_PARAM_API_KEY) apiKey: String = UNKNOWN_API_KEY,
        @Query(QUERY_PARAM_FROM_SYMBOLS) fSyms: String,
        @Query(QUERY_PARAM_TO_SYMBOLS) tSyms: String = CURRENCY
    ): CoinInfoJsonContainerDto

    companion object {
        private const val QUERY_PARAM_API_KEY = "api_key"
        private const val QUERY_PARAM_LIMIT = "limit"
        private const val QUERY_PARAM_TO_SYMBOL = "tsym"
        private const val QUERY_PARAM_TO_SYMBOLS = "tsyms"
        private const val QUERY_PARAM_FROM_SYMBOLS = "fsyms"
        private const val UNKNOWN_API_KEY = ""
        private const val LIMIT_LOAD_CURRENCY = 10
        private const val CURRENCY = "USD"
    }
}