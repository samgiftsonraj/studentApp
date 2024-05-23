package com.example.greatify.model.detailModelclass


import com.google.gson.annotations.SerializedName

data class FoodAllergie(
    @SerializedName("alergy_name")
    val alergyName: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("medical_detail_token")
    val medicalDetailToken: String,
    @SerializedName("medical_type")
    val medicalType: String
)