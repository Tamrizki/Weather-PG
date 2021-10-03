package com.example.weather_pgtest.data.remote.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.weather_pgtest.data.remote.response.WeatherResponse
import com.example.weather_pgtest.data.remote.service.ApiService
import com.example.weather_pgtest.utils.State
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class WeatherRepository(
    private val apiService: ApiService
) {

    val weatherState : MutableLiveData<State<WeatherResponse>> = MutableLiveData()

    fun getWeather(compositeDisposable: CompositeDisposable,
                   lat: Double,
                   lon: Double
    ): LiveData<State<WeatherResponse>>{
        weatherState.value = State.Loading()
        compositeDisposable.add(
            apiService.getWeather(lat, lon)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    weatherState.value = State.Success(it)
                },{
                    weatherState.value = State.Error(it)
                }))
        return weatherState
    }

}