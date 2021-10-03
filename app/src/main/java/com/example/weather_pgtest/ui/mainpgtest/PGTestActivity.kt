package com.example.weather_pgtest.ui.mainpgtest

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.weather_pgtest.R
import com.example.weather_pgtest.databinding.ActivityPgtestBinding
import com.example.weather_pgtest.ui.base.BaseActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class PGTestActivity : BaseActivity<ActivityPgtestBinding>(
    ActivityPgtestBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBavigation()
    }

    private fun setupBavigation(){
        val navView: BottomNavigationView = binding.bottomNav

        val navController = findNavController(R.id.nav_host_fragment_main)

        navView.setupWithNavController(navController)
    }
}