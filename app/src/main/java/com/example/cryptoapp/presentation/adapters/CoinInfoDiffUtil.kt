package com.example.cryptoapp.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.cryptoapp.domain.model.CoinInfo

class CoinInfoDiffUtil: DiffUtil.ItemCallback<CoinInfo>() {
    override fun areItemsTheSame(oldItem : CoinInfo, newItem : CoinInfo) : Boolean {
        return oldItem.lastTradeId == newItem.lastTradeId
    }

    override fun areContentsTheSame(oldItem : CoinInfo, newItem : CoinInfo) : Boolean {
        return oldItem == newItem
    }
}