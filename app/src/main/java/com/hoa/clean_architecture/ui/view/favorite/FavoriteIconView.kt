package com.hoa.clean_architecture.ui.view.favorite

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.hoa.clean_architecture.R

class FavoriteIconView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {

    private val viewModel by lazy { FavoriteIconViewModel(rootView) }

    init {
        View.inflate(context, R.layout.view_favorite, this)
    }

    fun setHandler(handler: IFavoriteIcon) {
        viewModel.initialUI(handler)
    }
}