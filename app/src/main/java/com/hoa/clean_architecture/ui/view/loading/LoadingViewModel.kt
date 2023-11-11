package com.hoa.clean_architecture.ui.view.loading

import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.LifecycleOwner
import com.hoa.clean_architecture.R

class LoadingViewModel(private val rootView: View) {

    private val loading by lazy { rootView.findViewById<View>(R.id.loading_progress_bar) }

    fun initializeUI(handler: ILoadingView, lifecycleOwner: LifecycleOwner) {
        handler.loadingVisibility.observe(lifecycleOwner) { visibility ->
            loading.isVisible = visibility
        }
    }
}