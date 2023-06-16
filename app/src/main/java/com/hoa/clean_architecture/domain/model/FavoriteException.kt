package com.hoa.clean_architecture.domain.model

import com.hoa.clean_architecture.data.ApiException

/**
 * Just an illustration for UseCase Data Model which
 */
data class FavoriteException(
    val error: String
): ApiException(error)