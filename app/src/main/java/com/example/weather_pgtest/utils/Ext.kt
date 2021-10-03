package com.example.weather_pgtest.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import java.util.*

fun ImageView.loadImageFromUrl(url: String){
    val fullUrl = "${Cons.ICON_URL}${url}.png"
    Glide.with(context)
        .load(fullUrl)
        .centerCrop()
        .into(this)
}

fun degrees(): String = "\u2103"

fun getDay(dt: Int): String{
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = (dt*1000L)
    val day = when(calendar.get(Calendar.DAY_OF_WEEK)){
        1 -> "Sunday"
        2 -> "Monday"
        3 -> "Tuesday"
        4 -> "Wednesday"
        5 -> "Thursday"
        6 -> "Friday"
        else -> "Saturday"
    }
    val date = "$day ${calendar.get(Calendar.DAY_OF_MONTH)}, ${calendar.get(Calendar.MONTH)+1} ${calendar.get(
        Calendar.YEAR)}"
    return date
}

fun convertToCelcius(k: Double): String
        = (k-273.15).toString().take(4)+degrees()