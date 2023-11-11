package com.hoa.clean_architecture.ui.view.loading

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.lifecycle.LifecycleOwner
import com.hoa.clean_architecture.R
import com.hoa.clean_architecture.ui.base.BaseSharedView
import com.hoa.clean_architecture.ui.base.BaseViewHandler
import java.lang.IllegalArgumentException

class LoadingView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr),
    BaseSharedView {

    private val viewModel by lazy { LoadingViewModel(rootView) }

    init {
        View.inflate(context, R.layout.view_loading, this)
    }

    override fun bindViewHandler(handler: BaseViewHandler, lifecycleOwner: LifecycleOwner) {
        viewModel.initializeUI(handler as ILoadingView, lifecycleOwner)
    }
}