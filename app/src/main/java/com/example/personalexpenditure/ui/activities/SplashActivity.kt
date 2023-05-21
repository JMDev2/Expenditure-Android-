package com.example.personalexpenditure.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.navigation.findNavController
import com.example.personalexpenditure.R
import com.google.firebase.auth.FirebaseAuth

class SplashActivity : AppCompatActivity() {

//    private lateinit var auth: FirebaseAuth
//    private var mAuthListener: FirebaseAuth.AuthStateListener? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_splash)
//
//        // Initialize auth variable here
//        auth = FirebaseAuth.getInstance()
//
//        mAuthListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
//            val user = firebaseAuth.currentUser
//            if (user != null) {
//                // User is already logged in, start LoginActivity
//                val intent = Intent(this, MainActivity::class.java)
//                startActivity(intent)
//                finish()
//            } else {
//                // User is not logged in, start OnboardingActivity
//                val intent = Intent(this, OnboardingActivity::class.java)
//                startActivity(intent)
//                finish()
//            }
//        }
//
//        Handler(Looper.getMainLooper()).postDelayed({
//            auth.addAuthStateListener(mAuthListener!!)
//        }, 3000) // 3000 is the delayed time in milliseconds.
//    }
//
//    override fun onStart() {
//        super.onStart()
//        mAuthListener?.let { auth.addAuthStateListener(it) }
//    }
//
//    override fun onStop() {
//        super.onStop()
//        mAuthListener?.let { auth.removeAuthStateListener(it) }
//    }
}
