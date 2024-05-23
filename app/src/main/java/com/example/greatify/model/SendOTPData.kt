package com.example.greatify.model


import com.google.gson.annotations.SerializedName

data class SendOTPData(
    @SerializedName("data")
    val `data`: String = "",
    @SerializedName("message")
    val message: Int = 0,
    @SerializedName("status")
    val status: String = ""
)