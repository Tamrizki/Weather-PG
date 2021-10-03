package com.example.weather_pgtest

import android.app.Application
import com.example.weather_pgtest.di.myModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(myModule)
        }
    }
}