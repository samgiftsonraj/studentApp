package com.example.greatify.model.detailModelclass


import com.google.gson.annotations.SerializedName

data class SchoolModule(
    @SerializedName("is_school_have")
    val isSchoolHave: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("token")
    val token: String
)