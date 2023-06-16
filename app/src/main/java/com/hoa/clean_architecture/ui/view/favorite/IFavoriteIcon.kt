package com.hoa.clean_architecture.ui.view.favorite

import androidx.lifecycle.LiveData
import com.hoa.clean_architecture.data.model.MenuItem

interface IFavoriteIcon {
    /**
     * View Data Entity
     */
    val menuItem: LiveData<MenuItem>

    /**
     * Triggered upon user click on favorite icon
     */
    fun onFavoriteClicked(item: MenuItem) = Unit
}