package com.example.greatify.network.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.greatify.model.LogoutData
import com.example.greatify.model.SchoolListData
import com.example.greatify.model.SendOTPData
import com.example.greatify.model.VerifyOTPData
import com.google.gson.JsonObject
import okhttp3.ResponseBody
import org.json.JSONException
import org.json.JSONObject

class LoginRepository {
    private val instance = Retroinstance.Instance
    private val _schoolListLiveData = MutableLiveData<SchoolListData>()
    val dropDownListLiveData : LiveData<SchoolListData> = _schoolListLiveData
    val sendOtpLiveData = MutableLiveData<ApiResponse<SendOTPData>>()
    val verifyOtpLiveData = MutableLiveData<ApiResponse<VerifyOTPData>>()
    val logoutLiveData = MutableLiveData<ApiResponse<LogoutData>>()

    suspend fun fetchSchoolList() {
        try {
            val result = instance.getschoolList()
            Log.d("schoolList", "${result.body()}")
            if (result.isSuccessful) {
                _schoolListLiveData.value = result.body()
            } else {
                Log.d("Repo", "Error Fetching:${result.errorBody()} ")

            }
        } catch (e: Exception) {
            Log.d("Repo", "fetchSchoolList:${e.message} ")
        }
    }

    suspend fun sendOTP(
        schoolToken: String,
        countryCode: String,
        mobileNumber: String,
        school: String,
        studentId: String
    ) {
        try {
            val result = instance.sendOTP(schoolToken, countryCode, mobileNumber, school, studentId)
            Log.d("Repository", "Otp full data: ${result.body()}")
            if (result.isSuccessful) {
                sendOtpLiveData.value = ApiResponse.Success(result.body())
                Log.d("Repo", "Success Fetching OTP:${result.body()} ")

            } else {
                Log.d("Repo", "Error Fetching OTP:${result.code()} ")
                try {
                    sendOtpLiveData.value =
                        result.errorBody()?.let {
                            errorMessages(it)?.let { error ->
                                ApiResponse.Error(
                                    error
                                )
                            }
                        }
                } catch (e: JSONException) {
                    Log.d("Fail1", result.message())
                    sendOtpLiveData.value = ApiResponse.Error(result.message())
                    e.printStackTrace()
                }

            }
        } catch (e: Exception) {
            Log.d("Repository", "Exception fetching sendOtp: ${e.message}")
        }
    }

    suspend fun verifyOTP(
        mobileNumber: String,
        otp : String,
        countryCode: String,
        schoolToken: String,
        school: String,
        studentId: String
    ) {
        try {
            val result = instance.verifyOTP(mobileNumber, otp, countryCode, schoolToken, school, studentId)
            Log.d("verifyOtp", "What Happens: ${result.body()}")
            if (result.isSuccessful) {
                verifyOtpLiveData.value = ApiResponse.Success(result.body())
                Log.d("Repo", "Success verify OTP:${result.body()} ")

            } else {
                Log.d("Repo", "Error verify OTP:${result.code()} ")
                try {
                    verifyOtpLiveData.value =
                        result.errorBody()?.let {
                            errorMessages(it)?.let { error ->
                                ApiResponse.Error(
                                    error
                                )
                            }
                        }
                } catch (e: JSONException) {
                    Log.d("Fail1", result.message())
                    verifyOtpLiveData.value = ApiResponse.Error(result.message())
                    e.printStackTrace()
                }
            }
        } catch (e: Exception) {
            Log.d("Repository", "Exception fetching sendOtp: ${e.message}")
        }
    }


    suspend fun logOut(token: String, json: JsonObject) {
        logoutLiveData.value = ApiResponse.Loading(true)
        try {
            val result = instance.logoutApi("Bearer $token", json)
            Log.d("logout", "log out data : ${result.body()}")
            if (result.isSuccessful) {
                logoutLiveData.value = ApiResponse.Success(result.body())
                Log.d("logout", "logged out:${result.body()} ")

            } else {
                Log.d("Repo", "Error logout :${result.code()} ")
                try {
                    logoutLiveData.value =
                        result.errorBody()?.let {
                            errorMessages(it)?.let { error ->
                                ApiResponse.Error(
                                    error
                                )
                            }
                        }
                } catch (e: JSONException) {
                    Log.d("Fail1", result.message())
                    logoutLiveData.value = ApiResponse.Error(result.message())
                    e.printStackTrace()
                }
            }
        } catch (e: Exception) {
            Log.d("Repository", "Exception fetching sendOtp: ${e.message}")
        }
    }



    private fun errorMessages(responseBody: ResponseBody): String? {
        val errorJson = JSONObject(responseBody.string())
        return errorJson.getString("message")
    }


}

