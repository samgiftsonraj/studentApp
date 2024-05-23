package com.example.greatify.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.greatify.view.fragments.FridayFragment
import com.example.greatify.view.fragments.HomeFragment
import com.example.greatify.view.fragments.LearnFragment
import com.example.greatify.view.fragments.MarketplaceFragment
import com.example.greatify.view.fragments.MondayFragment
import com.example.greatify.view.fragments.SaturdayFragment
import com.example.greatify.view.fragments.ThursdayFragment
import com.example.greatify.view.fragments.TuesdayFragment
import com.example.greatify.view.fragments.WednesdayFragment

class TabFragmentPageAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 6
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0-> MondayFragment()
            1-> TuesdayFragment()
            2-> WednesdayFragment()
            3 -> ThursdayFragment()
            4 -> FridayFragment()
            else-> SaturdayFragment()
        }

    }
}