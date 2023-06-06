package com.hoa.clean_architecture.ui.view.loading

import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.LifecycleOwner
import com.hoa.clean_architecture.R

class LoadingViewModel constructor(
    private val rootView: View,
    private val lifecycleOwner: LifecycleOwner
) {

    private val loading: View by lazy { rootView.findViewById(R.id.loading_progress_bar) }

    fun initializeUI(handler: ILoadingView) {
        handler.loadingVisibility.observe(lifecycleOwner) { visibility ->
            loading.isVisible = visibility
        }
    }
}