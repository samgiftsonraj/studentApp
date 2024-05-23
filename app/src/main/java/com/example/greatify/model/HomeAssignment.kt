package com.example.greatify.model


import com.google.gson.annotations.SerializedName

class HomeAssignment : ArrayList<HomeAssignmentItem>()


data class HomeAssignmentItem(
    @SerializedName("assigments")
    val assigments: List<Assigment>,
    @SerializedName("subject_name")
    val subjectName: String
){
    data class Assigment(
        @SerializedName("class_subject_teacher_token")
        val classSubjectTeacherToken: String,
        @SerializedName("day_count")
        val dayCount: String,
        @SerializedName("description")
        val description: String,
        @SerializedName("due_date")
        val dueDate: String,
        @SerializedName("subject_name")
        val subjectName: String,
        @SerializedName("title")
        val title: String,
        @SerializedName("token")
        val token: String
    )
}


