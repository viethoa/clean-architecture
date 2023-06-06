package com.hoa.clean_architecture.ui.view.closeicon

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.hoa.clean_architecture.R
import com.hoa.clean_architecture.ui.extension.asDIParent
import com.hoa.clean_architecture.ui.extension.customViewLifeCycleOwner
import javax.inject.Inject

class CloseIconView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {

    @Inject
    lateinit var viewModel: CloseIconViewModel

    private val lifecycleOwner by customViewLifeCycleOwner()

    init {
        View.inflate(context, R.layout.view_close_button, this)
        DaggerCloseIconComponent.builder()
            .closeIconModule(CloseIconModule(rootView, lifecycleOwner))
            .closeIconDependency(asDIParent?.getDependency(CloseIconDependency::class))
            .build()
            .inject(this)
    }

    fun setHandler(handler: ICloseIcon) {
        viewModel.initializeUI(handler)
    }
}