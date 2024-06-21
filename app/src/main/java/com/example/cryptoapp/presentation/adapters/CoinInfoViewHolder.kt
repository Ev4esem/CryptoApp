package com.example.cryptoapp.presentation.adapters

import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoapp.R
import com.example.cryptoapp.databinding.ItemCoinInfoBinding
import com.example.cryptoapp.domain.model.CoinInfo

class CoinInfoViewHolder(val binding: ItemCoinInfoBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(coinPriceInfo : CoinInfo) {
        with(binding) {
            val symbolsTemplate = root.resources.getString(R.string.symbols_template)
            tvSymbols.text = String.format(
                symbolsTemplate,
                coinPriceInfo.fromSymbol,
                coinPriceInfo.toSymbol
            )
            tvPrice.text = coinPriceInfo.price
        }
    }
}