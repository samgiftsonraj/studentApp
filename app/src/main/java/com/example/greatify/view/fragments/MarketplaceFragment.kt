package com.example.greatify.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.greatify.R
import com.example.greatify.adapter.ProductsAdapter
import com.example.greatify.databinding.FragmentMarketplaceBinding
import com.example.greatify.model.recylerviewModelclass.Products


class MarketplaceFragment : Fragment() {
    private  var _binding : FragmentMarketplaceBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding  = FragmentMarketplaceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val products = listOf(
            Products(R.drawable.dell_laptop,"DELL 13th Gen Laptop","44,000","58,000"),
            Products(R.drawable.hp_lap_img,"HP 13th Gen Laptop","44,000","58,000"),
            Products(R.drawable.hp_lap_img,"HP 13th Gen Laptop","44,000","58,000"),
            Products(R.drawable.dell_laptop,"DELL 13th Gen Laptop","44,000","58,000"),
            Products(R.drawable.hp_lap_img,"HP 13th Gen Laptop","24,000","58,000"),
            Products(R.drawable.hp_lap_img,"HP 13th Gen Laptop","24,000","58,000"),
            Products(R.drawable.dell_laptop,"DELL 13th Gen Laptop","44,000","58,000"),
            Products(R.drawable.dell_laptop,"DELL 13th Gen Laptop","44,000","58,000"),
        )
        binding.rvProducts.setHasFixedSize(true)
        binding.rvProducts.adapter = ProductsAdapter(products)
    }
}