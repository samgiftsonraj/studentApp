package com.example.greatify.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.greatify.model.SchoolListData
import com.example.greatify.network.remote.LoginRepository
import com.google.gson.JsonObject

class LoginViewModel : ViewModel() {
    private val repository = LoginRepository()
    val dropdownList: LiveData<SchoolListData> = repository.dropDownListLiveData
    val sendOtpData by lazy { repository.sendOtpLiveData }
    val verifyOtpData by lazy { repository.verifyOtpLiveData }
    val logoutData by lazy { repository.logoutLiveData }
    val validationError = MutableLiveData<String>()

    suspend fun fetchSchoolData() {
        repository.fetchSchoolList()
    }

    suspend fun fetchOTP(
        schoolToken: String,
        countryCode: String,
        mobileNumber: String,
        school: String,
        studentId: String
    ) {
        if(mobileNumber.isEmpty() && studentId.isEmpty()){
            validationError.value = "Please enter the Mobile Number and StudentID"
        }
        else if(mobileNumber.isEmpty()){
            validationError.value = "Please enter the Mobile Number"
        }
        else if (studentId.isEmpty()){
            validationError.value = "Please enter the StudentID"
        }
        else{
            repository.sendOTP(schoolToken, countryCode, mobileNumber, school, studentId)

        }
    }

    suspend fun fetchVerifyOTP(
        mobileNumber: String,
        otp1: String,
        otp2: String,
        otp3: String,
        otp4: String,
        otp5: String,
        otp6: String,
        countryCode: String,
        schoolToken: String,
        school: String,
        studentId: String
    ){
        if(otp1.isEmpty() && otp2.isEmpty() && otp3.isEmpty() && otp4.isEmpty() && otp5.isEmpty() && otp6.isEmpty()){
            validationError.value = "Please enter the OTP"
        }
        else if(otp1.isEmpty() || otp2.isEmpty() || otp3.isEmpty() || otp4.isEmpty() || otp5.isEmpty() || otp6.isEmpty()){
            validationError.value = "Please fill the OTP"
        }
       else{
            val otp = otp1 + otp2 + otp3 + otp4 + otp5 + otp6
            repository.verifyOTP(mobileNumber, otp, countryCode, schoolToken, school, studentId)
        }
    }

    suspend fun logout(token: String,school: String){
            val json = JsonObject().apply {
                addProperty("school", school)
            }
            repository.logOut(token,json)
    }


}

