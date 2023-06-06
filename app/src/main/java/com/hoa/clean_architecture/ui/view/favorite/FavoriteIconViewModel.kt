package com.hoa.clean_architecture.ui.view.favorite

import android.view.View
import android.widget.ImageView
import androidx.lifecycle.LifecycleOwner
import com.hoa.clean_architecture.R
import com.hoa.clean_architecture.data.entity.MenuItem

class FavoriteIconViewModel constructor(
    private val rootView: View,
    private val lifecycleOwner: LifecycleOwner
) {

    private val favIcon: ImageView by lazy { rootView.findViewById(R.id.view_favorite_icon) }

    private var menuItem: MenuItem? = null

    fun initialUI(handler: IFavoriteIcon) {
        // Icon resource
        handler.menuItem.observe(lifecycleOwner) { item ->
            menuItem = item
            favIcon.setImageResource(
                when (item.isFavorite) {
                    true -> R.drawable.ic_favorite_filled_red
                    false -> R.drawable.ic_favorite_white
                }
            )
        }
        // Click Event
        favIcon.setOnClickListener {
            menuItem?.let { item ->
                handler.onFavoriteClicked(item)
            }
        }
    }
}