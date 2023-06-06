package com.hoa.clean_architecture.data

import dagger.Module
import dagger.Provides

@Module
object DataModule {

    @Provides
    fun provideMenuRepository(): MenuRepository {
        return MenuRepositoryImpl()
    }
}