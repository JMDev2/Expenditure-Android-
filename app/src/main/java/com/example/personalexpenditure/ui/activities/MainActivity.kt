package com.example.personalexpenditure.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import com.example.personalexpenditure.R
import com.example.personalexpenditure.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController
    private var mAuthListener: AuthStateListener? = null
    private var mAuth: FirebaseAuth? = null
    val firebaseAuth = FirebaseAuth.getInstance()
//    override fun onSupportNavigateUp(): Boolean {
//
//
//        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
//        val navController = navHostFragment.navController
//        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
//    }

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
             navController = navHostFragment.navController


        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

       // mAuth = FirebaseAuth.getInstance();


     //   authStateListener()


    }




    //authestate listener
//    val authStateListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
//        val firebaseUser = firebaseAuth.currentUser
//        if (firebaseUser == null) {
//            val signUpFragment = SignUpFragment()
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.fragmentContainerView, signUpFragment)
//                .addToBackStack(null)
//                .commit()
//
//
//        }
//    }

//    override fun onStart() {
//        super.onStart()
//        firebaseAuth!!.addAuthStateListener(this.authStateListener!!)
//    }


//    override fun onStop() {
//        super.onStop()
//        firebaseAuth!!.removeAuthStateListener(this.authStateListener!!)
//    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

//    override fun onStart() {
//        super.onStart()
//        mAuth?.addAuthStateListener(mAuthListener!!)
//    }
//
//    override fun onStop() {
//        super.onStop()
//        if (mAuthListener != null) {
//            mAuth?.removeAuthStateListener(mAuthListener!!)
//        }
//    }
}