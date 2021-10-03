package com.example.weather_pgtest.ui.pgtest3

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import com.example.weather_pgtest.R
import com.example.weather_pgtest.databinding.FragmentPGTest3Binding
import com.example.weather_pgtest.ui.base.BaseFragment

class PGTest3Fragment : BaseFragment<FragmentPGTest3Binding>(
    FragmentPGTest3Binding::inflate
) {

    private var list: MutableList<String> = ArrayList()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListener()
    }

    private fun setupListener() {
        binding?.apply {
            btnProcess.setOnClickListener {
                setupList(tieInput.text.toString())
            }
        }
    }

    private fun setupList(number: String) {
        list.clear()
        val listChar : List<Char> = number.toList()
        for (idx in 0 until listChar.size){
            var num = listChar[idx].digitToInt()
            for (n in 1 until (listChar.size-(idx))){
                num *= 10
            }
            list.add(num.toString())
        }
        binding?.lvNumber?.apply {
            adapter = ArrayAdapter(requireContext(), R.layout.support_simple_spinner_dropdown_item, list)
        }
    }
}