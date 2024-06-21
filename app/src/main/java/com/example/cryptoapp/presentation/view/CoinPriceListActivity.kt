package com.example.cryptoapp.presentation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.cryptoapp.R
import com.example.cryptoapp.databinding.ActivityCoinPrceListBinding
import com.example.cryptoapp.presentation.adapters.CoinInfoAdapter

class CoinPriceListActivity : AppCompatActivity() {

    private val viewModel : CoinViewModel by lazy {
        ViewModelProvider(this)[CoinViewModel::class.java]
    }
    private var _binding : ActivityCoinPrceListBinding? = null
    private val binding : ActivityCoinPrceListBinding
        get() = _binding ?: throw RuntimeException("CoinPriceListActivity == null")
    private val adapter by lazy {
        CoinInfoAdapter()
    }

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityCoinPrceListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter.onCoinClickListener = {
            launchFragment(it.fromSymbol)
        }
        binding.rvCoinPriceList.adapter = adapter
        viewModel.coinPriceList.observe(this) {
            adapter.submitList(it)
        }
    }

    private fun launchFragment(fromSymbol : String) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, CoinDetailFragment.newIntent(fromSymbol))
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
