package com.example.personalexpenditure.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.fragment.app.FragmentActivity
import javax.inject.Inject

object SharedPreferences {

    //get onboarding status
    fun getOnboardingStatus(context: Context): Boolean {
        return context.getSharedPreferences("profile", Context.MODE_PRIVATE)
            ?.getBoolean("onboarded", false)!!
    }

    // set onboarding status

    fun setOnboardingStatus(context: FragmentActivity?, onboarded: Boolean) {
        return context?.getSharedPreferences("profile", Context.MODE_PRIVATE)?.edit()
            ?.putBoolean("onboarded", onboarded)?.apply()!!
    }
//    val applicationContext = context.applicationContext
//
//    private var prefs: SharedPreferences =
//        context.getSharedPreferences(
//            "USER_DATA",
//            Context.MODE_PRIVATE
//        )
//
//    private var userDataList: SharedPreferences =
//        context.getSharedPreferences(
//            "USER_DATA",
//            Context.MODE_PRIVATE
//        )
//
//    companion object{
//        const val is_first_time: String = "is_first_time"
//        const val USER_NAME: String = "userName"
//        const val USER_EMAIL: String = "userEmail"
//    }
//
//    fun setIsFirstTimeUSer(isFirstTime: Boolean){
//        val editor = prefs.edit()
//        editor.putBoolean(is_first_time, isFirstTime)
//        editor.apply()
//    }
//
//    fun getIsFirstTimeUser(): Boolean{
//        return prefs.getBoolean(is_first_time, true)
//    }
//
//    fun saveStringData(key: String, value: String) {
//        val editor = prefs.edit()
//        editor.putString(key, value)
//        editor.apply()
//    }
//
//    fun getStringData(key: String): String {
//
//        return prefs.getString(key, "").toString()
//    }
}