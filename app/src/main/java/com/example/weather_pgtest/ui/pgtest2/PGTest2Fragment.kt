package com.example.weather_pgtest.ui.pgtest2

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.widget.doAfterTextChanged
import com.example.weather_pgtest.R
import com.example.weather_pgtest.databinding.FragmentPGTest2Binding
import com.example.weather_pgtest.ui.base.BaseFragment
import kotlin.collections.HashMap

class PGTest2Fragment : BaseFragment<FragmentPGTest2Binding>(
    FragmentPGTest2Binding::inflate
) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListener()
    }

    private fun setupListener() {
        binding?.apply {
            tieInput.doAfterTextChanged {
                tvQuestion.text = "Apakah \"${it.toString()}\" \ntermasuk kalimat Anagram?"
            }

            btnProcess.setOnClickListener {
                val text = tieInput.text.toString().lowercase().split(" ")
                if (isAnagram(text[0], text[1])){
                    imgResult.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_checked))
                }else{
                    imgResult.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_cancel))
                }
            }
        }
    }

    private fun isAnagram(text1: String, text2: String): Boolean{
        val map = HashMap<Char, Int>()
        for (char in text1.toCharArray()){
            if (map.containsKey(char)) map[char] = map[char]!! + 1
            else map[char] = 1
        }

        for (char in text2.toCharArray()){
            if (!map.containsKey(char)) return false
            else {
                map[char] = map[char]!! - 1
                if (map[char] == 0) map.remove(char)
            }
        }
        return map.isEmpty()
    }
}