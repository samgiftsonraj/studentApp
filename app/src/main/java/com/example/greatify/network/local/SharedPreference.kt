package com.example.greatify.network.local

import android.content.Context
import android.content.SharedPreferences
import com.example.greatify.R
import com.example.greatify.model.sharedPreference.DetailsData
import com.example.greatify.model.sharedPreference.PreferenceData

class SharedPreference(context: Context?) {
    private var sharedPreference: SharedPreferences? = context?.getSharedPreferences(context.getString(
        R.string.app_name), Context.MODE_PRIVATE)
    //student_token, section_token
    fun setDetails(student_token: String, section_token: String){
        sharedPreference?.edit()?.apply {
            putString("student_token", student_token)
            putString("section_token", section_token)
            apply()
        }
    }
    fun getDetails(): DetailsData {
        return DetailsData(
            sharedPreference?.getString("student_token", "").toString(),
            sharedPreference?.getString("section_token", "").toString()
        )
    }
    //push user data
    fun setUserData(name: String, token: String, school: String){
        sharedPreference?.edit()?.apply {
            putString("userName", name)
            putString("userToken", token)
            putString("SchoolName", school)
            apply()
        }
    }
    //get User data
    fun getUserData(): PreferenceData {
        return PreferenceData(
            sharedPreference?.getString("userToken", "").toString(),
            sharedPreference?.getString("SchoolName", "").toString()
        )
    }


}