package com.example.personalexpenditure.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.personalexpenditure.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener

class OnboardingActivity : AppCompatActivity() {

//    private lateinit var auth: FirebaseAuth
//    private var mAuthListener: FirebaseAuth.AuthStateListener? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main2)
//
//        // Initialize auth variable here
//        auth = FirebaseAuth.getInstance()

//        mAuthListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
//            val user = firebaseAuth.currentUser
//            if (user != null) {
//                val intent = Intent(this, LoginActivity::class.java)
//                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//                startActivity(intent)
//                finish()
//            } else {
//                val intent = Intent(this, OnboardingActivity::class.java)
//                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//                startActivity(intent)
//                finish()
//            }
//        }
    }

//    override fun onStart() {
//        super.onStart()
//        mAuthListener?.let { auth.addAuthStateListener(it) }
//    }
//
//    override fun onStop() {
//        super.onStop()
//        mAuthListener?.let { auth.removeAuthStateListener(it) }
//    }

