package com.hoa.clean_architecture.domain.mapper

import com.hoa.clean_architecture.data.ApiResponse
import com.hoa.clean_architecture.domain.model.FavoriteException
import java.lang.ClassCastException

fun ApiResponse<Any>.getFavoriteException(): String {
    if (this is ApiResponse.Error && this.exception is FavoriteException) {
        return this.exception.error
    }
    throw ClassCastException(
        "The ApiException is not type of FavoriteException, please check your logic"
    )
}