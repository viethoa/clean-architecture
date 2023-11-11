package com.hoa.clean_architecture.ui.screen.header

import android.view.View
import android.widget.ImageView
import com.hoa.clean_architecture.R
import com.hoa.clean_architecture.ui.view.closeicon.CloseIconView
import com.hoa.clean_architecture.ui.view.favorite.FavoriteIconView

class HeaderViewModel constructor(
    private val rootView: View
) {

    private val image: ImageView by lazy { rootView.findViewById(R.id.header_item_image) }
    private val closeButton: CloseIconView by lazy { rootView.findViewById(R.id.header_close_button) }
    private val favoriteButton: FavoriteIconView by lazy { rootView.findViewById(R.id.header_favorite_button) }

    fun initializeUI(handler: IHeaderView) {
        closeButton.setHandler(handler, handler.lifecycleOwner)
        favoriteButton.setHandler(handler)
        handler.observeData { item ->
            item.getItemImage()?.let { image.setImageResource(it) }
        }
    }
}