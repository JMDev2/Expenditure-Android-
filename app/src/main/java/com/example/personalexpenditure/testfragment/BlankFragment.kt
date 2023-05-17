package com.example.personalexpenditure.testfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import com.example.personalexpenditure.R
import com.example.personalexpenditure.databinding.FragmentBlankBinding
import com.example.personalexpenditure.databinding.FragmentMainBinding

class BlankFragment : Fragment() {

    private lateinit var binding: FragmentBlankBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBlankBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonClick()
    }

    private fun buttonClick() {
        binding.save.setOnClickListener {
            binding.updateFragemnent.visibility = GONE
        }
    }


}