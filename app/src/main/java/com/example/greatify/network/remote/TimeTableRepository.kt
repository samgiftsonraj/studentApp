package com.example.greatify.network.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.greatify.model.TimetabelData
import com.example.greatify.model.detailModelclass.detailData

class TimeTableRepository {
    private val instance = Retroinstance.Instance
     val timeTableLiveData = MutableLiveData<TimetabelData>()
    val timeTable : LiveData<TimetabelData> = timeTableLiveData
    suspend fun getTimeTable(token:String, student_token:String,school:String) {
            try {
                val result = instance.timeTableApi("Bearer $token",student_token,school)
                Log.d("timeTableRep", "getTimeTable: ${result.body()}")
                if (result.isSuccessful) {
                    timeTableLiveData.value = result.body()
                    Log.d("timeTableRep", "Success: ${result.body()} ")
                }
                else{
                    val error = result.errorBody()?.string()
                    Log.d("timeTableRep", "Failure: $error ")
                }
            }catch (e : Exception){
                Log.d("error", "getTimeTable: ${e.message}")
            }
    }
}