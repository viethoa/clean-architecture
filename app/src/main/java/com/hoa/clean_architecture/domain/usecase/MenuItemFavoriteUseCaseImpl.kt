package com.hoa.clean_architecture.domain.usecase

import com.hoa.clean_architecture.data.ApiResponse
import com.hoa.clean_architecture.data.repository.MenuItemRepository
import com.hoa.clean_architecture.data.model.MenuItem
import com.hoa.clean_architecture.domain.model.FavoriteException

val FAKE_FAVORITE_EXCEPTION = FavoriteException("Favorite Exception")

class MenuItemFavoriteUseCaseImpl constructor(
    private val menuRepository: MenuItemRepository
) : MenuItemFavoriteUseCase {

    override suspend fun switchFavorite(item: MenuItem): ApiResponse<Any> {
        val response = when (item.isFavorite) {
            true -> menuRepository.unFavorite(item)
            false -> menuRepository.addItemToFavorite(item)
        }
        if (response is ApiResponse.Error) {
            return ApiResponse.Error(FAKE_FAVORITE_EXCEPTION)
        }
        return response
    }
}