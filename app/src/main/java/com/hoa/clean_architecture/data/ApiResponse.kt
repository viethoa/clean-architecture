package com.hoa.clean_architecture.data

import java.lang.Exception

sealed class ApiResponse<out T> {
    data class Success<T>(val data: T) : ApiResponse<T>()
    data class Error<T>(val exception: Exception) : ApiResponse<T>()
}