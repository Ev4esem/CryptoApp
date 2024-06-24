package com.example.cryptoapp.presentation.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cryptoapp.R
import com.example.cryptoapp.databinding.FragmentPriceListBinding
import com.example.cryptoapp.presentation.adapters.CoinInfoAdapter

class CoinPriceListFragment: Fragment() {

    private var _binding : FragmentPriceListBinding? = null
    private val binding : FragmentPriceListBinding
        get() = _binding ?: throw RuntimeException("v == null")
    private val adapter by lazy {
        CoinInfoAdapter()
    }
    private val viewModel : MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreateView(
        inflater : LayoutInflater,
        container : ViewGroup?,
        savedInstanceState : Bundle?
    ) : View {
        _binding = FragmentPriceListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view : View, savedInstanceState : Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeRV()
    }

    private fun initializeRV() {
        binding.rvCoinPriceList.layoutManager = LinearLayoutManager(requireContext())
        binding.rvCoinPriceList.adapter = adapter
        viewModel.coinInfoList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        adapter.onCoinClickListener = {
            if (binding.containerLand == null) {
                launchFragment(
                    it.fromSymbol ?: UNDEFINED_FROM_SYMBOL,
                    R.id.container
                )
            } else {
                launchFragment(
                    it.fromSymbol ?: UNDEFINED_FROM_SYMBOL,
                    R.id.container_land
                )
            }
        }
    }

    private fun launchFragment(fromSymbol : String, container: Int) {
        parentFragmentManager.popBackStack()
        parentFragmentManager
            .beginTransaction()
            .replace(container, CoinDetailFragment.newIntent(fromSymbol))
            .addToBackStack(null)
            .commit()
    }


    companion object {
        private const val UNDEFINED_FROM_SYMBOL = ""

        fun newIntent() : CoinPriceListFragment = CoinPriceListFragment()

    }

}