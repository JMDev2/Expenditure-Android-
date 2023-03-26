package com.example.personalexpenditure.ui.fragments

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.personalexpenditure.R
import com.example.personalexpenditure.databinding.FragmentSignUpBinding


class SignUpFragment : Fragment() {
    private lateinit var binding: FragmentSignUpBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSignUpBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registerUser()
    }

   // validating the inputs
    private fun registerUser(){
        binding.signUp.setOnClickListener {
            val name = binding.signUpUsername.text.toString().trim()
            val email = binding.signUpemail.text.toString().trim()
            val password = binding.signUpPassword.text.toString().trim()


            if (name.isEmpty()){
                binding.signUpUsername.error = "Name is required"
                binding.signUpUsername.requestFocus()
                return@setOnClickListener
            }

            if (email.isEmpty()){
                binding.signUpemail.error = "Email is required"
                binding.signUpemail.requestFocus()
                return@setOnClickListener
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                binding.signUpemail.error = "Please provide a valid email address"
                binding.signUpemail.requestFocus()
                return@setOnClickListener
            }

            if(password.isEmpty()){
                binding.signUpPassword.error = "Password is required"
                binding.signUpPassword.requestFocus();
                return@setOnClickListener
            }
            if(password.length < 6 && password.isEmpty()){
                binding.signUpPassword.error = "Min Password length should be 6 characters"
                binding.signUpPassword.requestFocus();
                return@setOnClickListener
            }
        }
    }



}