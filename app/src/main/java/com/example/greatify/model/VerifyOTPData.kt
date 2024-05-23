package com.example.greatify.model


import com.google.gson.annotations.SerializedName

data class VerifyOTPData(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: Int,
    @SerializedName("status")
    val status: String
)

data class Data(
    @SerializedName("message")
    val message: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("token")
    val token: String
)