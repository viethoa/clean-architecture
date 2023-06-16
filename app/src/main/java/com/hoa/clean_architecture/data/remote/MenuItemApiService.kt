package com.hoa.clean_architecture.data.remote

import com.hoa.clean_architecture.data.ApiResponse
import com.hoa.clean_architecture.data.model.MenuItem

interface MenuItemApiService {
    /**
     * Get [MenuItem] Detail
     */
    suspend fun getMenuItem(itemId: Int): ApiResponse<MenuItem>

    /**
     * Add a [MenuItem] into user favorite
     */
    suspend fun addItemToFavorite(item: MenuItem): ApiResponse<Any>

    /**
     * Remove a [MenuItem] from user favorite
     */
    suspend fun unFavorite(item: MenuItem): ApiResponse<Any>
}