package com.hoa.clean_architecture.ui.screen.body

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.hoa.clean_architecture.R
import com.hoa.clean_architecture.ui.extension.customViewLifeCycleOwner

class BodyView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : ConstraintLayout(context, attrs, defStyle) {

    private val lifecycleOwner by customViewLifeCycleOwner()
    private val viewModel by lazy { BodyViewModel(this, lifecycleOwner) }

    init {
        View.inflate(context, R.layout.view_main_body, this)
    }

    fun setViewHandler(handler: IBodyView) {
        viewModel.initializeUI(handler)
    }
}