package com.example.greatify.model.detailModelclass


import com.google.gson.annotations.SerializedName


data class AcademicDetail(
    @SerializedName("board")
    val board: String,
    @SerializedName("department")
    val department: String,
    @SerializedName("institution_name")
    val institutionName: String,
    @SerializedName("percentage")
    val percentage: String,
    @SerializedName("qualification")
    val qualification: String,
    @SerializedName("student_education_info_token")
    val studentEducationInfoToken: String,
    @SerializedName("student_token")
    val studentToken: String,
    @SerializedName("total_mark")
    val totalMark: String,
    @SerializedName("year")
    val year: String
)