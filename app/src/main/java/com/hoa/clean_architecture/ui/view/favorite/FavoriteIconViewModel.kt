package com.hoa.clean_architecture.ui.view.favorite

import android.view.View
import android.widget.ImageView
import androidx.annotation.VisibleForTesting
import com.hoa.clean_architecture.R
import com.hoa.clean_architecture.data.model.MenuItem

class FavoriteIconViewModel constructor(
    private val rootView: View
) : View.OnClickListener {

    private val favIcon by lazy { rootView.findViewById<ImageView>(R.id.view_favorite_icon) }

    @VisibleForTesting(VisibleForTesting.PRIVATE)
    var menuItem: MenuItem? = null
    @VisibleForTesting(VisibleForTesting.PRIVATE)
    var handler: IFavoriteIcon? = null

    fun initialUI(handler: IFavoriteIcon) {
        this.handler = handler
        // Icon resource
        handler.observeData { item ->
            menuItem = item
            favIcon.setImageResource(
                when (item.isFavorite) {
                    true -> R.drawable.ic_favorite_filled_red
                    false -> R.drawable.ic_favorite_white
                }
            )
        }
        // Click Event
        favIcon.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        menuItem?.let { item ->
            handler?.onFavoriteClicked(item)
        }
    }
}