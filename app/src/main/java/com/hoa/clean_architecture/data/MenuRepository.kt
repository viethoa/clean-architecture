package com.hoa.clean_architecture.data

import com.hoa.clean_architecture.data.entity.MenuItem

interface MenuRepository {
    /**
     * Get [MenuItem] data
     */
    suspend fun getMenuItem(): ApiResponse<MenuItem>

    /**
     * Add a [MenuItem] into user favorite list
     */
    suspend fun addItemToFavorite(item: MenuItem): ApiResponse<Any>

    /**
     * Remove [MenuItem] from user favorite list
     */
    suspend fun unFavorite(item: MenuItem): ApiResponse<Any>
}