package com.example.greatify.network.remote

import com.example.greatify.model.LogoutData
import com.example.greatify.model.SchoolListData
import com.example.greatify.model.SendOTPData
import com.example.greatify.model.VerifyOTPData
import com.example.greatify.model.detailModelclass.detailData
import com.example.greatify.model.HomeAssignment
import com.example.greatify.model.TimetabelData
import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiInterface {
    @GET("school-dropdown")
    suspend fun getschoolList(): Response<SchoolListData>

    @FormUrlEncoded
    @POST("send-otp")
    suspend fun sendOTP(
        @Field("school_token") school_token: String,
        @Field("country_code") country_code: String,
        @Field("mobile_number") mobile_number: String,
        @Field("school") school: String,
        @Field("student_id") student_id: String
    ): Response<SendOTPData>


    @FormUrlEncoded
    @POST("verify-otp")
    suspend fun verifyOTP(
        @Field("mobile_number") mobile_number: String,
        @Field("otp") otp: String,
        @Field("country_code") country_code: String,
        @Field("school_token") school_token: String,
        @Field("school") school: String,
        @Field("student_id") student_id: String
    ): Response<VerifyOTPData>

    @POST("logout")
    suspend fun logoutApi(
        @Header("Authorization") token: String,
        @Body params: JsonObject
    ): Response<LogoutData>

    @GET("detail/{school}")
    suspend fun detailApi(
        @Header("Authorization") token: String,
        @Path("school", encoded = true) school: String
    ) : Response<detailData>

    @POST("home-assigment")
    suspend fun homeAssignmentApi(
        @Header("Authorization") token: String,
        @Body params: JsonObject
    ): Response<HomeAssignment>


    @GET("timetable_fetch/{student_token}/{school}")
    suspend fun timeTableApi(
        @Header("Authorization") token: String,
        @Path("student_token", encoded = true) student_token: String,
        @Path("school", encoded = true) school: String
    ) : Response<TimetabelData>


}