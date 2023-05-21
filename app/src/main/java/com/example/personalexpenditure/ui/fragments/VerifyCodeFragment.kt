package com.example.personalexpenditure.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.personalexpenditure.R

import com.example.personalexpenditure.databinding.FragmentVerifyCodeBinding


class VerifyCodeFragment : Fragment() {
    private lateinit var binding: FragmentVerifyCodeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentVerifyCodeBinding.inflate(inflater, container, false )

        return binding.root
    }


}