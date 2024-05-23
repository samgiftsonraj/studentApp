package com.example.greatify.model.detailModelclass


import com.google.gson.annotations.SerializedName

data class detailData(
    @SerializedName("data")
    val `data`: List<Datax>,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
){
    data class Datax(
        @SerializedName("academic_details")
        val academicDetails: List<AcademicDetail>,
        @SerializedName("AcademicYear")
        val academicYear: String,
        @SerializedName("academicyeartoken")
        val academicyeartoken: String,
        @SerializedName("address")
        val address: String,
        @SerializedName("annual_income")
        val annualIncome: String,
        @SerializedName("blood_group")
        val bloodGroup: String,
        @SerializedName("date_of_birth")
        val dateOfBirth: String,
        @SerializedName("email_id")
        val emailId: String,
        @SerializedName("emergency_contact_number")
        val emergencyContactNumber: String,
        @SerializedName("father_alter_number")
        val fatherAlterNumber: Any,
        @SerializedName("father_email_id")
        val fatherEmailId: String,
        @SerializedName("father_mobile_number")
        val fatherMobileNumber: String,
        @SerializedName("father_name")
        val fatherName: String,
        @SerializedName("gender")
        val gender: String,
        @SerializedName("image")
        val image: String,
        @SerializedName("medical_detail")
        val medicalDetail: MedicalDetail,
        @SerializedName("medicine_detail")
        val medicineDetail: List<Any>,
        @SerializedName("mobile_number")
        val mobileNumber: String,
        @SerializedName("mother_alter_number")
        val motherAlterNumber: Any,
        @SerializedName("mother_email_id")
        val motherEmailId: String,
        @SerializedName("mother_income")
        val motherIncome: String,
        @SerializedName("mother_mobile_number")
        val motherMobileNumber: String,
        @SerializedName("mother_name")
        val motherName: String,
        @SerializedName("mother_occupation")
        val motherOccupation: String,
        @SerializedName("name")
        val name: String,
        @SerializedName("occupation")
        val occupation: String,
        @SerializedName("school_id")
        val schoolId: String,
        @SerializedName("school_modules")
        val schoolModules: List<SchoolModule>,
        @SerializedName("school_token")
        val schoolToken: String,
        @SerializedName("SectionName")
        val sectionName: String,
        @SerializedName("SectionToken")
        val sectionToken: String,
        @SerializedName("StandardName")
        val standardName: String,
        @SerializedName("StandardToken")
        val standardToken: String,
        @SerializedName("student_id")
        val studentId: String,
        @SerializedName("token")
        val token: String
    )
}