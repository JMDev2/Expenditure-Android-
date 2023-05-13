package com.example.personalexpenditure.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.personalexpenditure.databinding.FragmentLoginBinding
import com.example.personalexpenditure.ui.activities.MainActivity
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





        //authStateListener()

        navigation()
        validateUser()
    }

   // authestate listener
//    val authStateListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
//        val firebaseUser = firebaseAuth.currentUser
//        if (firebaseUser != null) {
//            val intent = Intent(activity, MainActivity::class.java)
//            startActivity(intent)
//
//        }
//    }


//    override fun onStart() {
//        super.onStart()
//        firebaseAuth!!.addAuthStateListener(this.authStateListener!!)
//    }
//
//    override fun onStop() {
//        super.onStop()
//        firebaseAuth!!.removeAuthStateListener(this.authStateListener!!)
//    }

    //authestate listener
//    private fun authStateListener() {
//        mAuthListener = AuthStateListener { firebaseAuth ->
//            val user = firebaseAuth.currentUser
//            if (user != null) {
//                // Navigate to main fragment
//                val action = LoginFragmentDirections.actionLoginFragmentToMainFragment()
//                findNavController().navigate(action)
//            }
//        }
//    }

    private fun navigation(){
//        binding.navogateToSignup.setOnClickListener {
//            val action = LoginFragmentDirections.
//            findNavController().navigate(action)
//        }

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