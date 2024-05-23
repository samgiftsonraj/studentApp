package com.example.greatify.viewmodel

import androidx.lifecycle.ViewModel
import com.example.greatify.network.remote.HomeRepository
import com.google.gson.JsonObject

class HomeViewModel : ViewModel(){
    private val repository = HomeRepository()
    val homeassignmentData by lazy { repository.homeAssignmentLiveData }
    val studentData by lazy { repository.studentDetails }

    suspend fun fetchStudentDetails(token: String, school: String){
        repository.studentData(token, school)
    }
    suspend fun homeassignment(token:String,student_token: String, section_token: String,  school :String){
        val json = JsonObject().apply {
            addProperty("student_token", student_token)
            addProperty("section_token", section_token)
            addProperty("school", school)
        }
        repository.fetchAssignmentList(token,json)
    }

}