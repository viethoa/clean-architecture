package com.hoa.clean_architecture.data

import android.content.Context
import com.hoa.clean_architecture.data.local.ApplicationDatabase
import com.hoa.clean_architecture.data.remote.MenuItemApiService
import com.hoa.clean_architecture.data.remote.MenuItemApiServiceImpl
import com.hoa.clean_architecture.data.repository.MenuItemRepository
import com.hoa.clean_architecture.data.repository.MenuItemRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
object DataModule {

    @Provides
    fun provideDatabaseInstance(context: Context): ApplicationDatabase =
        ApplicationDatabase.getDatabase(context)

    @Provides
    fun provideMenuItemApiService(): MenuItemApiService = MenuItemApiServiceImpl()

    @Provides
    fun provideMenuRepository(
        applicationDatabase: ApplicationDatabase,
        menuItemApiService: MenuItemApiService
    ): MenuItemRepository {
        return MenuItemRepositoryImpl(
            applicationDatabase.menuItemDao(),
            menuItemApiService
        )
    }
}