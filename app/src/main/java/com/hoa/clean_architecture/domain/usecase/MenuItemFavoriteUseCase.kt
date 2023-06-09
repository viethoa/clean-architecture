package com.hoa.clean_architecture.domain.usecase

import com.hoa.clean_architecture.data.ApiResponse
import com.hoa.clean_architecture.data.model.MenuItem

interface MenuItemFavoriteUseCase {
    /**
     * 1. If item is favorite item then un-favorite it
     * 2. If item is not a favorite item then add it into favorite list
     */
    suspend fun switchFavorite(item: MenuItem): ApiResponse<Any>
}