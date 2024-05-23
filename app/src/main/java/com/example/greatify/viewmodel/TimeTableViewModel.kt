package com.example.greatify.viewmodel

import androidx.lifecycle.ViewModel
import com.example.greatify.network.remote.TimeTableRepository

class TimeTableViewModel : ViewModel() {
    private val repository = TimeTableRepository()
    val timeTableData by lazy { repository.timeTable }

    suspend fun fetchTimeTable(token: String, student_token: String, school: String) {
        repository.getTimeTable(token,student_token,school)
    }

}