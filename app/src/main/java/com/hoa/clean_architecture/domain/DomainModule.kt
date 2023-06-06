package com.hoa.clean_architecture.domain

import com.hoa.clean_architecture.data.MenuRepository
import dagger.Module
import dagger.Provides

@Module
object DomainModule {

    @Provides
    fun provideMenuItemFavoriteUseCase(repository: MenuRepository): MenuItemFavoriteUseCase {
        return MenuItemFavoriteUseCaseImpl(repository)
    }
}