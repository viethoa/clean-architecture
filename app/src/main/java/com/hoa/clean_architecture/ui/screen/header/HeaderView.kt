package com.hoa.clean_architecture.ui.screen.header

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.hoa.clean_architecture.R

class HeaderView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : ConstraintLayout(context, attrs, defStyle) {

    private val viewModel by lazy { HeaderViewModel(this) }

    init {
        View.inflate(context, R.layout.view_main_header, this)
    }

    fun setViewHandler(handler: IHeaderView) {
        viewModel.initializeUI(handler)
    }
}