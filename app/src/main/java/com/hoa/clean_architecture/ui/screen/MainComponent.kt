package com.hoa.clean_architecture.ui.screen

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.hoa.clean_architecture.component.Navigator
import com.hoa.clean_architecture.component.NavigatorImpl
import com.hoa.clean_architecture.data.DataModule
import com.hoa.clean_architecture.data.repository.MenuItemRepository
import com.hoa.clean_architecture.domain.DomainModule
import com.hoa.clean_architecture.domain.usecase.MenuItemFavoriteUseCase
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
class MainModule(
    private val screen: MainActivity,
    private val lifecycleOwner: LifecycleOwner
) {

    @Provides
    fun provideContext(): Context = screen.applicationContext

    @Provides
    fun provideNavigator(): Navigator {
        return NavigatorImpl(screen)
    }

    @Provides
    fun provideMainViewModel(
        repository: MenuItemRepository,
        useCase: MenuItemFavoriteUseCase
    ): MainViewModel {
        return MainViewModel(lifecycleOwner, repository, useCase)
    }
}