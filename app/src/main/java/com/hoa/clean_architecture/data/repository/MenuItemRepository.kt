package com.hoa.clean_architecture.data.repository

import com.hoa.clean_architecture.data.ApiResponse
import com.hoa.clean_architecture.data.model.MenuItem
import kotlinx.coroutines.flow.Flow

interface MenuItemRepository {
    /**
     * Get [MenuItem] data
     */
    suspend fun getMenuItem(itemId: Int): Flow<ApiResponse<MenuItem>>

    /**
     * Add a [MenuItem] into user favorite list
     */
    suspend fun addItemToFavorite(item: MenuItem): ApiResponse<Any>

    /**
     * Remove [MenuItem] from user favorite list
     */
    suspend fun unFavorite(item: MenuItem): ApiResponse<Any>
}