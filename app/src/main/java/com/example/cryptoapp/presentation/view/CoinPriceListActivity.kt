package com.example.cryptoapp.presentation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cryptoapp.R
import com.example.cryptoapp.databinding.ActivityCoinPrceListBinding
import com.example.cryptoapp.presentation.adapters.CoinInfoAdapter

class CoinPriceListActivity : AppCompatActivity() {

    private var _binding : ActivityCoinPrceListBinding? = null
    private val binding : ActivityCoinPrceListBinding
        get() = _binding ?: throw RuntimeException("CoinPriceListActivity == null")

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityCoinPrceListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        launchFragment()
    }

    private fun launchFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, CoinPriceListFragment.newIntent())
            .commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
