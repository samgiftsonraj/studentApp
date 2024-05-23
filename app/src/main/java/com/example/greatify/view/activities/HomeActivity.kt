package com.example.greatify.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.greatify.R
import com.example.greatify.databinding.ActivityHomeBinding
import com.example.greatify.view.fragments.ProfileFragment
import com.example.greatify.view.fragments.HomeFragment
import com.example.greatify.view.fragments.LearnFragment
import com.example.greatify.view.fragments.MarketplaceFragment

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setCurrentFragment(HomeFragment())
        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home -> setCurrentFragment(HomeFragment())
                R.id.marketplace -> setCurrentFragment(MarketplaceFragment())
                R.id.learn -> setCurrentFragment(LearnFragment())
                R.id.profile -> setCurrentFragment(ProfileFragment())
            }
            true
        }
    }

    private fun setCurrentFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_fragment,fragment)
            commit()
        }
    }
}