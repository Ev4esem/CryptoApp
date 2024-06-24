package com.example.cryptoapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.cryptoapp.R
import com.example.cryptoapp.data.mapper.BASE_IMAGE_URL
import com.example.cryptoapp.databinding.ItemCoinInfoBinding
import com.example.cryptoapp.domain.model.CoinInfo
import com.squareup.picasso.Picasso

class CoinInfoAdapter : ListAdapter<CoinInfo, CoinInfoViewHolder>(CoinInfoDiffUtil()) {

    var onCoinClickListener : ((CoinInfo) -> Unit)? = null

    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int) : CoinInfoViewHolder {
        val binding = ItemCoinInfoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CoinInfoViewHolder(binding)
    }

    override fun onBindViewHolder(holder : CoinInfoViewHolder, position : Int) {
        val coin = getItem(position)
        val binding = holder.binding
        holder.bind(coin)
        binding.root.setOnClickListener {
            onCoinClickListener?.invoke(coin)
        }
        val lastUpdateTemplate = binding.root.resources.getString(R.string.last_update_template)
        binding.tvLastUpdate.text = String.format(lastUpdateTemplate, coin.lastUpdate)
        Picasso.get().load(coin.imageUrl).into(binding.ivLogoCoin)
    }

}