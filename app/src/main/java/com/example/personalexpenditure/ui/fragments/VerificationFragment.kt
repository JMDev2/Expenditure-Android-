package com.example.personalexpenditure.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.personalexpenditure.R
import com.example.personalexpenditure.databinding.FragmentOnboardingBinding
import com.example.personalexpenditure.databinding.FragmentVerificationBinding


class VerificationFragment : Fragment() {
    private lateinit var binding: FragmentVerificationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentVerificationBinding.inflate(inflater, container, false )

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navigateToVerificationCode()
    }


    private fun navigateToVerificationCode() {
        binding.submitPhone.setOnClickListener {
            // Navigate to verification code fragment
            val action = VerificationFragmentDirections.actionVerificationFragmentToVerifyCodeFragment()
            findNavController().navigate(action)
        }

    }


}