package com.hoa.clean_architecture.ui.view.closeicon

import android.view.View
import androidx.lifecycle.LifecycleOwner
import com.hoa.clean_architecture.component.Navigator
import com.hoa.clean_architecture.component.ResourcesProvider
import com.hoa.clean_architecture.component.ResourcesProviderImpl
import dagger.Component
import dagger.Module
import dagger.Provides

@Component(
    modules = [CloseIconModule::class],
    dependencies = [CloseIconDependency::class]
)
interface CloseIconComponent {
    fun inject(view: CloseIconView)
}

interface CloseIconDependency {
    fun navigator(): Navigator
}

@Module
class CloseIconModule(
    private val rootView: View,
    private val lifecycleOwner: LifecycleOwner
) {

    @Provides
    fun provideResourceProvider(): ResourcesProvider {
        return ResourcesProviderImpl(rootView.context)
    }

    @Provides
    fun provideCloseIconViewModel(
        navigator: Navigator,
        resourcesProvider: ResourcesProvider
    ): CloseIconViewModel {
        return CloseIconViewModel(
            rootView,
            navigator,
            lifecycleOwner,
            resourcesProvider
        )
    }
}