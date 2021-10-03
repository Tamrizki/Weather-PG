package com.example.weather_pgtest.ui.pgtest1

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weather_pgtest.data.model.PGTest1Model
import com.example.weather_pgtest.databinding.FragmentPGTest1Binding
import com.example.weather_pgtest.ui.base.BaseFragment

class PGTest1Fragment : BaseFragment<FragmentPGTest1Binding>(
    FragmentPGTest1Binding::inflate
) {

    private lateinit var pgAdapter: PGTest1Adapter
    private var list : MutableList<PGTest1Model> = ArrayList()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupList()
        setupListener()
    }

    private fun setupListener() {
        binding?.apply {
            btnProcess.setOnClickListener {
                setupData(tieInput.text.toString().toInt())
            }
        }
    }

    private fun setupData(number: Int) {
        list.clear()
        for (idx in 1..number){
            val desc = if (idx%6 == 0) "DIGITAL OASIS" else if (idx%3 == 0) "OS" else if (idx%2 == 0) "DI" else ""
            list.add(PGTest1Model(idx, desc))
        }
        pgAdapter.setData(list)
    }

    private fun setupList(){
        pgAdapter = PGTest1Adapter()
        binding?.rvPgtest1?.apply {
            adapter = pgAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }
}