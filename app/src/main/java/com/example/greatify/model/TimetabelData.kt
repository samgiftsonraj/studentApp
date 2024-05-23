package com.example.greatify.model


import com.google.gson.annotations.SerializedName

class TimetabelData : ArrayList<TimetabelDataItem>()

data class TimetabelDataItem(
    @SerializedName("category")
    val category: String,
    @SerializedName("duration")
    val duration: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("period_token")
    val periodToken: String,
    @SerializedName("periods")
    val periods: List<Any>,
    @SerializedName("type")
    val type: String
)