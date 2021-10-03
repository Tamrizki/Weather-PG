package com.example.weather_pgtest.ui.weather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weather_pgtest.data.remote.response.DailyItem
import com.example.weather_pgtest.data.remote.response.WeatherResponse
import com.example.weather_pgtest.data.viewmodel.WeatherViewModel
import com.example.weather_pgtest.databinding.ActivityWeatherBinding
import com.example.weather_pgtest.ui.base.BaseActivity
import com.example.weather_pgtest.ui.weatherdetail.WeatherDetailActivity
import com.example.weather_pgtest.utils.*
import org.jetbrains.anko.startActivity
import org.koin.android.ext.android.inject

class WeatherActivity : BaseActivity<ActivityWeatherBinding>(
    ActivityWeatherBinding::inflate
) {
    private val vm: WeatherViewModel by inject()
    private lateinit var weatherAdapter: WeatherAdapter
    private var location  = "-"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupObserver()
    }

    private fun setupObserver(){
        vm.getWeather(33.44, -94.04).observe(this, {
            when(it){
                is State.Loading -> Log.d("TAGTAGTAG", "onLoading: ")
                is State.Success -> {
                    Log.d("TAGTAGTAG", "on success ${it.data}")
                    setupView(it.data)
                }
                is State.Error -> Log.d("TAGTAGTAG", "onError: ${it.throwable}")
            }
        })
    }

    private fun setupView(data: WeatherResponse) {
        binding.apply {
            tvLocation.text = data.timezone
            val current = data.current
            tvToday.text = getDay(current?.dt!!)
            tvTemp.text = convertToCelcius(current.temp!!)
            tvTempSmall.text = convertToCelcius(current.feelsLike!!)
            tvWeatherDesc.text = current.weather?.get(0)!!.main
            ivWeather.loadImageFromUrl("${current.weather[0]?.icon}")
            location = data.timezone!!
            setupList(data.daily)
        }
    }

    private fun setupList(daily: List<DailyItem>?) {
        weatherAdapter = WeatherAdapter(this, object : WeatherAdapter.onAdapterListener{
            override fun onClick(data: DailyItem) {
                startActivity<WeatherDetailActivity>(
                    "_weatherDaily" to data,
                    "_location" to location)
            }
        })

        binding.rvWeather.apply {
            adapter = weatherAdapter
            layoutManager = LinearLayoutManager(this@WeatherActivity)
            setHasFixedSize(true)
        }
        weatherAdapter.setData(daily!!)
    }
}