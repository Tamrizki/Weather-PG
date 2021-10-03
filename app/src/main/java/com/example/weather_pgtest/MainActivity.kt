package com.example.weather_pgtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.weather_pgtest.databinding.ActivityMainBinding
import com.example.weather_pgtest.ui.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding>(
    ActivityMainBinding::inflate
){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}