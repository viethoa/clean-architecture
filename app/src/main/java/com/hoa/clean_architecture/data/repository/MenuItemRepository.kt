package com.hoa.clean_architecture.data.repository

import com.hoa.clean_architecture.data.ApiResponse
import com.hoa.clean_architecture.data.model.MenuItem
import kotlinx.coroutines.flow.Flow

interface MenuItemRepository {
    /**
     * Get [MenuItem] data
     * 1. Get data from local DB first, do not returns anything if no data
     * 2. Get data from remote
     *  - If Success, store remote data into local DB and return that data
     *  - If error, return [ApiResponse.Error]
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