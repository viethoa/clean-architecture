package com.hoa.clean_architecture.ui.fake

import com.hoa.clean_architecture.data.ApiResponse
import com.hoa.clean_architecture.data.model.MenuItem
import com.hoa.clean_architecture.data.repository.MenuItemRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakeMenuRepository(apiResponse: ApiResponse<MenuItem>) : MenuItemRepository {

    private val menuItemStateFlow = MutableStateFlow(apiResponse)

    override suspend fun getMenuItem(itemId: Int): Flow<ApiResponse<MenuItem>> = menuItemStateFlow

    override suspend fun addItemToFavorite(item: MenuItem): ApiResponse<Any> {
        return ApiResponse.Success(Any())
    }

    override suspend fun unFavorite(item: MenuItem): ApiResponse<Any> {
        return ApiResponse.Success(Any())
    }
}