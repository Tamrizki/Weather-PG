package com.example.weather_pgtest.ui.weather

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.ethanhua.skeleton.Skeleton
import com.ethanhua.skeleton.SkeletonScreen
import com.example.weather_pgtest.R
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
    private var sDay : SkeletonScreen? = null
    private var sTemp : SkeletonScreen? = null
    private var sList : SkeletonScreen? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupList()
        setupObserver()
        setupListener()
    }

    private fun setupListener() {
        binding.apply {
            swRefresh.setOnRefreshListener {
                swRefresh.isRefreshing = true
                setupObserver()
            }
        }
    }

    private fun setupObserver(){
        vm.getWeather(33.44, -94.04).observe(this, {
            when(it){
                is State.Loading -> showSkeleton()
                is State.Success -> {
                    hideSkeleton()
                    setupView(it.data)
                }
                is State.Error -> hideSkeleton()
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
            weatherAdapter.setData(data.daily!!)

        }
    }

    private fun setupList() {
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
    }

    private fun showSkeleton(){
        if (sDay == null){
            sDay = Skeleton.bind(binding.tvToday).load(R.layout.skeleton_text_small).show()
            sTemp = Skeleton.bind(binding.tvTemp).load(R.layout.skeleton_text_big).show()
            sList = Skeleton.bind(binding.rvWeather).adapter(weatherAdapter).load(R.layout.skeleton_list).show()
        }else{
            sDay?.show()
            sTemp?.show()
            sList?.show()
        }
    }

    private fun hideSkeleton(){
        sDay?.hide()
        sTemp?.hide()
        sList?.hide()
        binding.swRefresh.isRefreshing = false
    }
}