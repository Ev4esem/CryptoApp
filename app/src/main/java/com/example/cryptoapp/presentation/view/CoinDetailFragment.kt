package com.example.cryptoapp.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.cryptoapp.databinding.ActivityCoinDetailBinding
import com.example.cryptoapp.domain.model.CoinInfo
import com.example.cryptoapp.presentation.extensions.toConvertTimestampToTime
import com.example.cryptoapp.presentation.extensions.toGetFullImageUrl
import com.squareup.picasso.Picasso

class CoinDetailFragment : Fragment() {

    private val viewModel: CoinViewModel by lazy {
        ViewModelProvider(this)[CoinViewModel::class.java]
    }
    private var _binding: ActivityCoinDetailBinding? = null
    private val binding: ActivityCoinDetailBinding
        get() = _binding ?: throw RuntimeException("CoinDetailFragment == null")

    override fun onCreateView(
        inflater : LayoutInflater,
        container : ViewGroup?,
        savedInstanceState : Bundle?
    ) : View {
        _binding = ActivityCoinDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view : View, savedInstanceState : Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.coinPriceInfo.observe(viewLifecycleOwner) {
            initializeData(it)
        }
    }


    private fun initializeData(coinPriceInfo : CoinInfo) {
        with(binding) {
            tvPrice.text = coinPriceInfo.price
            tvMinPrice.text = coinPriceInfo.lowDay
            tvMaxPrice.text = coinPriceInfo.highDay
            tvLastMarket.text = coinPriceInfo.lastMarket
            tvLastUpdate.text = coinPriceInfo.lastUpdate.toConvertTimestampToTime()
            tvFromSymbol.text = coinPriceInfo.fromSymbol
            tvToSymbol.text = coinPriceInfo.toSymbol
            Picasso.get().load(coinPriceInfo.imageUrl.toGetFullImageUrl()).into(ivLogoCoin)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        private const val EXTRA_FROM_SYMBOL = "fSym"
        fun newIntent(fromSymbol: String): CoinDetailFragment {
            return CoinDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(EXTRA_FROM_SYMBOL, fromSymbol)
                }
            }
        }
    }
}
