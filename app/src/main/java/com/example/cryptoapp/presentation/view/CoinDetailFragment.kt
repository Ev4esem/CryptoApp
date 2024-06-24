package com.example.cryptoapp.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.cryptoapp.databinding.ActivityCoinDetailBinding
import com.example.cryptoapp.domain.model.CoinInfo
import com.squareup.picasso.Picasso

class CoinDetailFragment : Fragment() {

    private var fromSymbol: String? = null

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        fromSymbol = arguments?.getString(EXTRA_FROM_SYMBOL)
    }

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
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
        viewModel.getDetailInfo(fromSymbol ?: UNKNOWN_PARAM).observe(viewLifecycleOwner) {
            initializeData(it)
        }

    }


    private fun initializeData(coinPriceInfo : CoinInfo) {
        with(binding) {
            tvPrice.text = coinPriceInfo.price
            tvMinPrice.text = coinPriceInfo.lowDay
            tvMaxPrice.text = coinPriceInfo.highDay
            tvLastMarket.text = coinPriceInfo.lastMarket
            tvLastUpdate.text = coinPriceInfo.lastUpdate
            tvFromSymbol.text = coinPriceInfo.fromSymbol
            tvToSymbol.text = coinPriceInfo.toSymbol
            Picasso.get().load(coinPriceInfo.imageUrl).into(ivLogoCoin)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        private const val UNKNOWN_PARAM = ""
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
