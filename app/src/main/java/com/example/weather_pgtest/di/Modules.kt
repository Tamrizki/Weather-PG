package com.example.weather_pgtest.di

import com.example.weather_pgtest.data.remote.repo.WeatherRepository
import com.example.weather_pgtest.data.remote.service.ApiService
import com.example.weather_pgtest.data.remote.service.AuthInterceptor
import com.example.weather_pgtest.data.remote.service.createWebService
import com.example.weather_pgtest.data.remote.service.provideOkHttpClient
import com.example.weather_pgtest.data.viewmodel.WeatherViewModel
import com.example.weather_pgtest.utils.Cons
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { AuthInterceptor(androidContext()) }
    single { provideOkHttpClient(get(), androidContext()) }
    single { createWebService<ApiService>(get(), Cons.BASE_URL) }
}

val repoModul = module {
    single { WeatherRepository(get()) }
}

val viewModelModule = module {
    viewModel { WeatherViewModel(get()) }
}

val myModule = listOf(
    appModule,
    repoModul,
    viewModelModule
)