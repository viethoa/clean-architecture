package com.hoa.clean_architecture.ui.screen

import com.hoa.clean_architecture.component.Navigator
import com.hoa.clean_architecture.component.NavigatorImpl
import com.hoa.clean_architecture.data.DataModule
import com.hoa.clean_architecture.data.MenuRepository
import com.hoa.clean_architecture.domain.DomainModule
import com.hoa.clean_architecture.domain.MenuItemFavoriteUseCase
import com.hoa.clean_architecture.ui.view.closeicon.CloseIconDependency
import dagger.Component
import dagger.Module
import dagger.Provides

@Component(
    modules = [
        DataModule::class,
        DomainModule::class,
        MainModule::class
    ]
)
interface MainComponent : CloseIconDependency {
    fun inject(activity: MainActivity)
}

@Module
class MainModule(private val screen: MainActivity) {

    @Provides
    fun provideNavigator(): Navigator {
        return NavigatorImpl(screen)
    }

    @Provides
    fun provideMainViewModel(
        repository: MenuRepository,
        useCase: MenuItemFavoriteUseCase
    ): MainViewModel {
        return MainViewModel(repository, useCase)
    }
}