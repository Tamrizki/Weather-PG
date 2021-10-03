package com.example.weather_pgtest.ui.weather

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weather_pgtest.data.remote.response.DailyItem
import com.example.weather_pgtest.databinding.ItemRowWeatherBinding
import com.example.weather_pgtest.utils.convertToCelcius
import com.example.weather_pgtest.utils.getDay
import com.example.weather_pgtest.utils.loadImageFromUrl

class WeatherAdapter(
    val context: Context,
    val listener: onAdapterListener
    ): RecyclerView.Adapter<WeatherAdapter.VHolder>() {

    private var list : MutableList<DailyItem> = ArrayList()

    class VHolder(
        val binding: ItemRowWeatherBinding
        ): RecyclerView.ViewHolder(binding.root) {
        fun bind(d: DailyItem) {
            binding.apply {
                tvDay.text = getDay(d.dt!!)
                tvTemp.text = convertToCelcius(d.temp?.day!!)
                tvTempSmall.text = convertToCelcius(d.temp.min!!)
                tvWeatherDesc.text = d.weather?.get(0)?.main
                ivWeather.loadImageFromUrl("${d.weather?.get(0)?.icon}")
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHolder = VHolder(
        ItemRowWeatherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: VHolder, position: Int) {
        holder.bind(list[position])
        holder.itemView.setOnClickListener {
            listener.onClick(list[position])
        }
    }

    override fun getItemCount(): Int = list.size

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newList: List<DailyItem>){
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    interface onAdapterListener{
        fun onClick(data: DailyItem)
    }
}