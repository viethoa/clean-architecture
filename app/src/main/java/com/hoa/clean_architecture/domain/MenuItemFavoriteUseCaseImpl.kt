package com.hoa.clean_architecture.domain

import com.hoa.clean_architecture.data.ApiResponse
import com.hoa.clean_architecture.data.MenuRepository
import com.hoa.clean_architecture.data.entity.MenuItem

class MenuItemFavoriteUseCaseImpl constructor(
    private val menuRepository: MenuRepository
) : MenuItemFavoriteUseCase {

    override suspend fun switchFavorite(item: MenuItem): ApiResponse<Any> {
        return when (item.isFavorite) {
            true -> menuRepository.unFavorite(item)
            false -> menuRepository.addItemToFavorite(item)
        }
    }
}