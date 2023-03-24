package com.example.personalexpenditure.ui.fragments

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.personalexpenditure.R
import com.example.personalexpenditure.databinding.FragmentLoginBinding
import com.example.personalexpenditure.databinding.FragmentSignUpBinding


class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        validateUser()
    }

    //validating the inputs
    private fun validateUser(){
        binding.signUp.setOnClickListener {
            val name = binding.loginUsername.text.toString().trim()
            val password = binding.loginPassword.text.toString().trim()


            if (name.isEmpty()){
                binding.loginUsername.error = "Name is required"
                binding.loginUsername.requestFocus()
                return@setOnClickListener
            }


            if(password.isEmpty()){
                binding.loginPassword.error = "Password is required"
                binding.loginPassword.requestFocus();
                return@setOnClickListener
            }
            if(password.length < 6 && password.isEmpty()){
                binding.loginPassword.error = "Min Password length should be 6 characters"
                binding.loginPassword.requestFocus();
                return@setOnClickListener
            }
        }
    }
}