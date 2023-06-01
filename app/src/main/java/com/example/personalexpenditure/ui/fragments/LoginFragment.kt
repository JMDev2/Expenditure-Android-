package com.example.personalexpenditure.ui.fragments

import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.personalexpenditure.R
import com.example.personalexpenditure.databinding.FragmentLoginBinding
import com.example.personalexpenditure.utils.SharedPreferences
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener


class  LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var auth : FirebaseAuth
    private var mAuthListener: AuthStateListener? = null


    val firebaseAuth = FirebaseAuth.getInstance()


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
        auth = FirebaseAuth.getInstance()

//        SharedPreferences(requireContext()).saveStringData(SharedPreferences.USER_EMAIL, binding.loginEmail.text.toString())


        //authStateListener()

        navigation()
        validateUser()
        eyeToggle()
    }

    private fun eyeToggle() {
        var isPasswordVisible = false
        binding.imageToggle3.setOnClickListener {
            isPasswordVisible = !isPasswordVisible

            if (isPasswordVisible) {
                // Show password
                binding.loginPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
                binding.imageToggle3.setImageResource(R.drawable.baseline_visibility_off_24)
            } else {
                // Hide password
                binding.loginPassword.transformationMethod = PasswordTransformationMethod.getInstance()
                binding.imageToggle3.setImageResource(R.drawable.baseline_visibility_24)
            }

            // Move the cursor to the end of the text
            binding.loginPassword.setSelection(binding.loginPassword.text.length)
        }
    }


    private fun navigation(){
        binding.navogateToSignup.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToSignUpFragment()
            findNavController().navigate(action)
        }

    }

    //validating the inputs
    private fun validateUser(){
        binding.signButton.setOnClickListener {
            val email = binding.loginEmail.text.toString().trim()
            val password = binding.loginPassword.text.toString().trim()


            if (email.isEmpty()){
                binding.loginEmail.error = "Email is required"
                binding.loginEmail.requestFocus()
                return@setOnClickListener
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                binding.loginEmail.error = "Please provide a valid email address"
                binding.loginEmail.requestFocus()
                return@setOnClickListener
            }


            if (password.length < 6) {
                binding.loginPassword.error = "Min Password length should be 6 characters"
                binding.loginPassword.requestFocus();
                return@setOnClickListener
            }



            binding.progressBar2.visibility = View.VISIBLE
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful) {
                    // Navigate to main fragment
                    val action = LoginFragmentDirections.actionLoginFragmentToMainFragment()
                    findNavController().navigate(action)

                } else {
                    Toast.makeText(activity, "something went wrong, try again", Toast.LENGTH_SHORT)
                        .show()
                    binding.progressBar2.visibility = View.GONE
                }
            }
                .addOnFailureListener { e ->
                    Log.e("LoginFragment", "Error logging in", e)
                    Toast.makeText(activity, "Error logging in: ${e.message}", Toast.LENGTH_SHORT).show()
                }
        }

    }

}