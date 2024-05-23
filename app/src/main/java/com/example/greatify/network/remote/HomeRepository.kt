package com.example.greatify.network.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.greatify.model.detailModelclass.detailData
import com.example.greatify.model.HomeAssignment
import com.google.gson.JsonObject
import okhttp3.ResponseBody
import org.json.JSONObject

class HomeRepository {
    private val instance = Retroinstance.Instance
    private val _studentDetails = MutableLiveData<detailData>()
    val studentDetails: LiveData<detailData> = _studentDetails
    val homeAssignmentLiveData = MutableLiveData<ApiResponse<HomeAssignment>>()

    suspend fun studentData(token: String, school: String) {
        try {
            val result = instance.detailApi("Bearer $token", school)
            Log.d("Repository", "What Happens: ${result.body()}")
            if (result.isSuccessful) {
                _studentDetails.value = result.body()
                Log.d("Repository", "Success: ${result.body()}")
            } else {
                val errorMsg = result.errorBody()?.let { errorMessages(it) } ?: "Unknown error"
                Log.d("Repository", "Error fetching studentDetailsError: $errorMsg")
            }
        } catch (e: Exception) {
            Log.d("Repository", "Exception fetching StudentDetails: ${e.message}")
        }
    }

    suspend fun fetchAssignmentList(token: String, json: JsonObject) {
        homeAssignmentLiveData.value = ApiResponse.Loading(true)
        try {
            val result = instance.homeAssignmentApi("Bearer $token", json)
            Log.d("Repository", "What Happens: ${result.body()}")
            if (result.isSuccessful && result.body() != null) {
                homeAssignmentLiveData.value = ApiResponse.Success(result.body()!!)
                Log.d("Repository", "Success assignment body: ${result.body()}")
            } else {
                val errorMsg = result.errorBody()?.let { errorMessages(it) } ?: "Unknown error"
                homeAssignmentLiveData.value = ApiResponse.Loading(false)
                homeAssignmentLiveData.value = ApiResponse.Error(errorMessages = errorMsg)
                Log.d("Repository", "Error fetching assignmentError: $errorMsg")
            }
        } catch (e: Exception) {
            homeAssignmentLiveData.value = ApiResponse.Loading(false)
            homeAssignmentLiveData.value = ApiResponse.Error(errorMessages = e.message ?: "Exception occurred")
            Log.d("Repository", "Exception fetching assignment: ${e.message}")
        }
    }

    private fun errorMessages(responseBody: ResponseBody): String {
        return try {
            val errorJson = JSONObject(responseBody.string())
            errorJson.getString("message")
        } catch (e: Exception) {
            "Error parsing error message"
        }
    }
}
