package com.hoa.clean_architecture.ui.view.fake

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hoa.clean_architecture.R
import com.hoa.clean_architecture.data.entity.MenuItem
import com.hoa.clean_architecture.ui.view.closeicon.CloseIcon
import com.hoa.clean_architecture.ui.view.closeicon.CloseIconType
import com.hoa.clean_architecture.ui.view.closeicon.ICloseIcon
import com.hoa.clean_architecture.ui.view.favorite.IFavoriteIcon
import com.hoa.clean_architecture.ui.view.loading.ILoadingView

class FakeHandler : ILoadingView, ICloseIcon, IFavoriteIcon {

    private val _visibility = MutableLiveData<Boolean>()
    override val loadingVisibility: LiveData<Boolean> = _visibility

    private val _closeIconData = MutableLiveData<CloseIcon>()
    override val data: LiveData<CloseIcon> = _closeIconData

    private var favoriteClick = 0
    private val _menuItem = MutableLiveData<MenuItem>()
    override val menuItem: LiveData<MenuItem> = _menuItem

    override fun onFavoriteClicked(item: MenuItem) {
        favoriteClick += 1
    }

    fun verifyFavoriteClick(time: Int): Boolean {
        return time == favoriteClick
    }

    fun hideLoading() {
        _visibility.value = false
    }

    fun showLoading() {
        _visibility.value = true
    }

    fun setRedCircleCloseIcon() {
        _closeIconData.value = CloseIcon(
            type = CloseIconType.CIRCLE_CLOSE,
            tintColor = R.color.red
        )
    }

    fun setBluishCloseIcon() {
        _closeIconData.value = CloseIcon(
            type = CloseIconType.CLOSE,
            tintColor = R.color.bluish
        )
    }

    fun setFavoriteItem() {
        _menuItem.value = MenuItem(
            id = 101,
            name = "name",
            description = "description",
            isFavorite = true
        )
    }

    fun setUnFavoriteItem() {
        _menuItem.value = MenuItem(
            id = 1011,
            name = "name",
            description = "description",
            isFavorite = false
        )
    }
}