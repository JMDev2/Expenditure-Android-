package com.example.personalexpenditure.ui.activities

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import com.example.personalexpenditure.R
import com.example.personalexpenditure.databinding.ActivityMainBinding
import com.example.personalexpenditure.utils.SharedPreferences
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


    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController

        val sharedPrefs = SharedPreferences.getOnboardingStatus(this)

        navController.graph = navHostFragment.navController.navInflater.inflate(R.navigation.nav_graph).apply {
            if (sharedPrefs){
                setStartDestination(R.id.loginFragment)
            } else {
                setStartDestination(R.id.onboardingFragment)
            }
        }


        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)




    }



    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }




    //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
    //l3tm31n@h0m3
}