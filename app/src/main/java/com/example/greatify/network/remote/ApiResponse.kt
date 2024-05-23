package com.example.greatify.network.remote

sealed class ApiResponse<T>(
    val data: T? = null,
    val errorMessages: String? = null,
    val showLoader: Boolean? = null
) {
    class Loading<T>(showLoader: Boolean?) : ApiResponse<T>(showLoader = showLoader)
    class Success<T>(data: T? = null) : ApiResponse<T>(data = data)
    class Error<T>(errorMessages: String) : ApiResponse<T>(errorMessages = errorMessages)

}