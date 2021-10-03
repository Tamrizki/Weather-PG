package com.example.weather_pgtest.ui.main

import android.os.Bundle
import com.example.weather_pgtest.databinding.ActivityMainBinding
import com.example.weather_pgtest.ui.base.BaseActivity
import com.example.weather_pgtest.ui.mainpgtest.PGTestActivity
import com.example.weather_pgtest.ui.weather.WeatherActivity
import org.jetbrains.anko.startActivity

class MainActivity : BaseActivity<ActivityMainBinding>(
    ActivityMainBinding::inflate
){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupListener()
    }

    private fun setupListener() {
        binding.apply {
            btnPgt.setOnClickListener {
                startActivity<PGTestActivity>()
            }
            btnWeather.setOnClickListener {
                startActivity<WeatherActivity>()
            }
        }
    }
}