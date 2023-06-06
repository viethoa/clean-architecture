package com.hoa.clean_architecture.ui.view.loading

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.hoa.clean_architecture.R
import com.hoa.clean_architecture.ui.extension.customViewLifeCycleOwner

class LoadingView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val lifecycleOwner by customViewLifeCycleOwner()
    private val viewModel by lazy { LoadingViewModel(rootView, lifecycleOwner) }

    init {
        View.inflate(context, R.layout.view_loading, this)
    }

    fun setViewHandler(handler: ILoadingView) {
        viewModel.initializeUI(handler)
    }
}