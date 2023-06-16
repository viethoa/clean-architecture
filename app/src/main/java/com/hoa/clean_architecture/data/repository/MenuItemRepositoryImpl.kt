package com.hoa.clean_architecture.data.repository

import com.hoa.clean_architecture.data.ApiResponse
import com.hoa.clean_architecture.data.SourceType
import com.hoa.clean_architecture.data.local.MenuItemDao
import com.hoa.clean_architecture.data.model.MenuItem
import com.hoa.clean_architecture.data.remote.MenuItemApiService
import com.hoa.clean_architecture.data.mapper.toEntity
import com.hoa.clean_architecture.data.mapper.toMenuItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MenuItemRepositoryImpl constructor(
    private val menuItemDao: MenuItemDao,
    private val menuItemApiService: MenuItemApiService
) : MenuItemRepository {

    override suspend fun getMenuItem(itemId: Int): Flow<ApiResponse<MenuItem>> =
        flow {
            // Get local data
            menuItemDao.getMenuItem(itemId)
                ?.let {
                    emit(ApiResponse.Success(it.toMenuItem(), SourceType.LOCAL))
                }
            // Get remote data and update local
            val response = menuItemApiService.getMenuItem(itemId)
            if (response is ApiResponse.Success) {
                menuItemDao.addMenuItem(response.data.toEntity())
            }
            emit(response)
        }
            .flowOn(Dispatchers.IO)

    override suspend fun addItemToFavorite(item: MenuItem): ApiResponse<Any> {
        return menuItemApiService.addItemToFavorite(item)
    }

    override suspend fun unFavorite(item: MenuItem): ApiResponse<Any> {
        return menuItemApiService.unFavorite(item)
    }
}