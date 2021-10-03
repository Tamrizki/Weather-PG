package com.example.weather_pgtest.ui.pgtest1

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weather_pgtest.data.model.PGTest1Model
import com.example.weather_pgtest.databinding.ItemRowPgt1Binding

class PGTest1Adapter() : RecyclerView.Adapter<PGTest1Adapter.VHolder>(){

    var list : MutableList<PGTest1Model> = ArrayList()

    class VHolder(
        val binding: ItemRowPgt1Binding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: PGTest1Model) {
            binding.apply {
                tvDesc.text = data.desc ?: " "
                tvNumber.text = data.number.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHolder = VHolder(
        ItemRowPgt1Binding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: VHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newList: List<PGTest1Model>){
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }
}