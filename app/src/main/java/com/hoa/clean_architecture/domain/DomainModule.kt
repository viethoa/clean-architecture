package com.hoa.clean_architecture.domain

import com.hoa.clean_architecture.data.repository.MenuItemRepository
import com.hoa.clean_architecture.domain.usecase.MenuItemFavoriteUseCase
import com.hoa.clean_architecture.domain.usecase.MenuItemFavoriteUseCaseImpl
import dagger.Module
import dagger.Provides

@Module
object DomainModule {

    @Provides
    fun provideMenuItemFavoriteUseCase(repository: MenuItemRepository): MenuItemFavoriteUseCase {
        return MenuItemFavoriteUseCaseImpl(repository)
    }
}