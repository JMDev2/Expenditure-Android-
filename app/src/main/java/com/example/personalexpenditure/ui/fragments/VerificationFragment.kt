package com.example.personalexpenditure.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import com.example.personalexpenditure.databinding.FragmentVerificationBinding
import com.example.personalexpenditure.utils.SharedPreferences
import com.google.firebase.auth.FirebaseAuth


class VerificationFragment : Fragment() {
    private lateinit var binding: FragmentVerificationBinding

    private lateinit var sendOTPBtn : TextView
    private lateinit var phoneNumberET : EditText
    private lateinit var auth : FirebaseAuth
    private lateinit var number : String
    private lateinit var mProgressBar : ProgressBar

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
//        val email= SharedPreferences(requireContext()).getStringData(SharedPreferences.USER_EMAIL)
//        Log.e("",email)
    }


    private fun navigateToVerificationCode() {
        binding.submitPhone.setOnClickListener {
            // Navigate to verification code fragment
//            val action = VerificationFragmentDirections.actionVerificationFragmentToVerifyCodeFragment()
//            findNavController().navigate(action)
        }

    }


}