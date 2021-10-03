package com.example.weather_pgtest.ui.weatherdetail

import android.content.Intent
import android.os.Bundle
import com.example.weather_pgtest.R
import com.example.weather_pgtest.data.remote.response.DailyItem
import com.example.weather_pgtest.databinding.ActivityWeatherDetailBinding
import com.example.weather_pgtest.ui.base.BaseActivity
import com.example.weather_pgtest.utils.convertToCelcius
import com.example.weather_pgtest.utils.getDay
import com.example.weather_pgtest.utils.loadImageFromUrl
import org.jetbrains.anko.toast
import java.lang.Exception

class WeatherDetailActivity : BaseActivity<ActivityWeatherDetailBinding>(
    ActivityWeatherDetailBinding::inflate
) {
    private val weather by lazy {
        intent.getParcelableExtra<DailyItem>("_weatherDaily")
    }

    private val location by lazy {
        intent.getStringExtra("_location")
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupView()
        setupListener()
        supportActionBar?.title= getString(R.string.weather)
    }

    private fun setupListener() {
        binding.btnShare.setOnClickListener {
            shareWA()
        }
    }

    private fun setupView() {
        weather?.let {
            binding.apply {
                tvDay.text = getDay(it.dt!!)
                tvHumidity.text = "Humidity - ${it.humidity}%"
                tvLocation.text = "Location - $location "
                tvPressure.text = "Pressure - ${it.pressure} hPa"
                tvTemp.text = convertToCelcius(it.temp?.day!!)
                tvTempSmall.text = convertToCelcius(it.temp.min!!)
                tvWeatherDesc.text = it.weather?.get(0)?.main
                tvWind.text = "Wind - ${it.windSpeed} Km/h NE"
                ivWeather.loadImageFromUrl(it.weather?.get(0)?.icon!!)
            }
        }
    }

    private fun shareWA(){
        try {
            val w = weather!!
            val intent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, "Today's Weather\n" +
                        "${getDay(w.dt!!)} - ${w.weather?.get(0)?.main}\n" +
                        "$location\n" +
                        "Temp : ${convertToCelcius(w.temp?.day!!)}")
            }
            startActivity(Intent.createChooser(intent, "Share"))
        }catch (e: Exception){
            toast("try again")
        }

    }
}