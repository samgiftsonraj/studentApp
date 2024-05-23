package com.example.greatify.model.detailModelclass

import com.google.gson.annotations.SerializedName

data class MedicalDetail(
    @SerializedName("food_allergie")
    val foodAllergie: List<FoodAllergie>,
    @SerializedName("health_condition")
    val healthCondition: List<HealthCondition>,
    @SerializedName("vaccine_allergie")
    val vaccineAllergie: List<Any>
)