package com.example.greatify.model


import com.google.gson.annotations.SerializedName

data class SchoolListData(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
) {

    data class Data(
        @SerializedName("address")
        val address: String,
        @SerializedName("city")
        val city: String,
        @SerializedName("database_name")
        val databaseName: String,
        @SerializedName("name")
        val name: String,
        @SerializedName("school_token")
        val schoolToken: String
    )
}
