package com.example.weather_pgtest.data.remote.service

import com.example.weather_pgtest.data.remote.response.WeatherResponse
import com.example.weather_pgtest.utils.Cons
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("data/2.5/onecall")
    fun getWeather(
       @Query("lat")lat: Double,
       @Query("lon")lon: Double,
       @Query("exclude")exclude: String? = "hourly,minutely",
       @Query("appid")appid: String = Cons.API_KEY
    ): Single<WeatherResponse>
}