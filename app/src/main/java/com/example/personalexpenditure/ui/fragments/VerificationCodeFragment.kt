package com.example.personalexpenditure.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.personalexpenditure.R
import com.example.personalexpenditure.databinding.FragmentVerificationBinding
import com.example.personalexpenditure.databinding.FragmentVerificationCodeBinding


class VerificationCodeFragment : Fragment() {
    private lateinit var binding: FragmentVerificationCodeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentVerificationCodeBinding.inflate(inflater, container, false)

        return binding.root
    }


}