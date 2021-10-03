package com.example.weather_pgtest.data.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.weather_pgtest.data.remote.repo.WeatherRepository
import com.example.weather_pgtest.data.remote.response.WeatherResponse
import com.example.weather_pgtest.utils.State
import com.example.weather_pgtest.utils.degrees
import io.reactivex.disposables.CompositeDisposable
import java.util.*

class WeatherViewModel(
    val repository: WeatherRepository
): ViewModel() {

    val compositeDisposable = CompositeDisposable()

    fun getWeather(lat: Double, lon: Double): LiveData<State<WeatherResponse>>
    = repository.getWeather(compositeDisposable, lat, lon)

}