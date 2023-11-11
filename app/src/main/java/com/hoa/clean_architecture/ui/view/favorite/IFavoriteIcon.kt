package com.hoa.clean_architecture.ui.view.favorite

import com.hoa.clean_architecture.data.model.MenuItem
import com.hoa.clean_architecture.ui.base.BaseView

interface IFavoriteIcon : BaseView<MenuItem> {
    /**
     * Triggered upon user click on favorite icon
     */
    fun onFavoriteClicked(item: MenuItem) = Unit
}