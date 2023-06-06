package com.hoa.clean_architecture.ui.screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hoa.clean_architecture.R
import com.hoa.clean_architecture.data.ApiResponse
import com.hoa.clean_architecture.data.MenuRepository
import com.hoa.clean_architecture.data.entity.MenuItem
import com.hoa.clean_architecture.domain.MenuItemFavoriteUseCase
import com.hoa.clean_architecture.ui.screen.body.IBodyView
import com.hoa.clean_architecture.ui.view.loading.ILoadingView
import com.hoa.clean_architecture.ui.view.closeicon.CloseIcon
import com.hoa.clean_architecture.ui.view.closeicon.CloseIconType
import com.hoa.clean_architecture.ui.screen.header.IHeaderView
import kotlinx.coroutines.launch

class MainViewModel constructor(
    private val repository: MenuRepository,
    private val favoriteUseCase: MenuItemFavoriteUseCase
) : ViewModel(),
    IHeaderView,
    IBodyView,
    ILoadingView {

    val uiState = MutableLiveData<MainUiState>()

    // Screen Loading
    private val _loading = MutableLiveData(false)
    override val loadingVisibility: LiveData<Boolean> = _loading

    // Back Button
    override val data: LiveData<CloseIcon> = MutableLiveData(
        CloseIcon(CloseIconType.CIRCLE_CLOSE, R.color.white)
    )

    // Header & Body
    private val _menuItem = MutableLiveData<MenuItem>()
    override val menuItem: LiveData<MenuItem> = _menuItem
    override val bodyContent: LiveData<MenuItem> = _menuItem

    fun getMenuItem() {
        _loading.value = true
        viewModelScope.launch {
            when (val result = repository.getMenuItem()) {
                is ApiResponse.Success -> {
                    _loading.value = false
                    _menuItem.value = result.data
                }
                is ApiResponse.Error -> {
                    _loading.value = false
                    uiState.value = MainUiState.GetMenuItemException
                }
            }
        }
    }

    override fun onFavoriteClicked(item: MenuItem) {
        _loading.value = true
        // Switch item favorite
        _menuItem.value = _menuItem.value?.switchFavoriteState()
        // Call Api to update
        viewModelScope.launch {
            when (favoriteUseCase.switchFavorite(item)) {
                is ApiResponse.Error -> {
                    _loading.value = false
                    _menuItem.value = _menuItem.value?.switchFavoriteState()
                    uiState.value = MainUiState.FavoriteException
                }
                is ApiResponse.Success -> {
                    _loading.value = false
                }
            }
        }
    }
}