package com.hoa.clean_architecture.data

import java.lang.Exception

const val DEFAULT_EXCEPTION = "Unknown Error"

open class ApiException(errorMessage: String = DEFAULT_EXCEPTION) : Exception(errorMessage)

enum class SourceType {
    REMOTE,
    LOCAL
}

fun SourceType.isRemote(): Boolean {
    return this == SourceType.REMOTE
}

sealed class ApiResponse<out T> {
    data class Success<T>(val data: T, val sourceType: SourceType = SourceType.REMOTE) :
        ApiResponse<T>()

    data class Error<T>(val exception: ApiException) : ApiResponse<T>()

    fun getErrorMessage(): String {
        return when (this) {
            is Error -> exception.localizedMessage ?: exception.message.orEmpty()
            else -> DEFAULT_EXCEPTION
        }
    }
}