package com.example.greatify.model


import com.google.gson.annotations.SerializedName

data class LogoutData(
    @SerializedName("data")
    val `data`: DataX,
    @SerializedName("message")
    val message: Any,
    @SerializedName("status")
    val status: String
)
data class DataX(
    @SerializedName("message")
    val message: String
)