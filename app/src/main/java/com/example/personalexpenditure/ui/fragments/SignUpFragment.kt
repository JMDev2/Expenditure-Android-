package com.example.personalexpenditure.ui.fragments

import android.content.ContentValues.TAG
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
import com.example.personalexpenditure.databinding.FragmentSignUpBinding
import com.example.personalexpenditure.model.User
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase


class SignUpFragment : Fragment() {
    private lateinit var binding: FragmentSignUpBinding
    private lateinit var auth : FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private var mAuthListener: FirebaseAuth.AuthStateListener? = null


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

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()

        authStateListener()
        registerUser()

    }


    //authestate listener
    private fun authStateListener() {
        mAuthListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            val user = firebaseAuth.currentUser
            if (user != null) {
                // Navigate to main fragment
                val action = LoginFragmentDirections.actionLoginFragmentToMainFragment()
                findNavController().navigate(action)
            }
        }
    }

   // validating the inputs
   private fun registerUser() {

       binding.signButton.setOnClickListener {
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

           if (password.isEmpty()){
               binding.signUpPassword.error = "Password is required"
               binding.signUpPassword.requestFocus()
               return@setOnClickListener
           }

           if (password.length < 6){
               binding.signUpPassword.error = "Minimum password length should be 6 characters"
               binding.signUpPassword.requestFocus()
               return@setOnClickListener
           }

           binding.progressBar3.visibility = View.VISIBLE
           auth.createUserWithEmailAndPassword(email, password)
               .addOnCompleteListener { task ->
                   if (task.isSuccessful) {
                       val databaseRef = database.reference.child("users").child(auth.currentUser!!.uid)
                       val users = User(name, email, auth.currentUser!!.uid)

                       databaseRef.setValue(users).addOnCompleteListener { dbTask ->
                           if (dbTask.isSuccessful) {
                               Toast.makeText(activity, "User registered successfully", Toast.LENGTH_SHORT).show()

                               // Navigate to login fragment
                               val action = SignUpFragmentDirections.actionSignUpFragmentToLoginFragment()
                               findNavController().navigate(action)

                           } else {
                               Toast.makeText(activity, "Failed to save user data to database", Toast.LENGTH_SHORT).show()
                               binding.progressBar3.visibility = View.GONE
                           }
                       }
                   } else {
                       Toast.makeText(activity, "Failed to register user: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                   }
               }
       }
   }


}