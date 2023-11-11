package com.hoa.clean_architecture.ui.screen

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hoa.clean_architecture.R
import com.hoa.clean_architecture.data.ApiResponse
import com.hoa.clean_architecture.data.isRemote
import com.hoa.clean_architecture.data.repository.MenuItemRepository
import com.hoa.clean_architecture.data.model.MenuItem
import com.hoa.clean_architecture.domain.usecase.MenuItemFavoriteUseCase
import com.hoa.clean_architecture.domain.mapper.getFavoriteException
import com.hoa.clean_architecture.ui.screen.body.IBodyView
import com.hoa.clean_architecture.ui.view.loading.ILoadingView
import com.hoa.clean_architecture.ui.view.closeicon.CloseIcon
import com.hoa.clean_architecture.ui.view.closeicon.CloseIconType
import com.hoa.clean_architecture.ui.screen.header.IHeaderView
import kotlinx.coroutines.launch

class MainViewModel constructor(
    private val screenLifecycleOwner: LifecycleOwner,
    private val repository: MenuItemRepository,
    private val favoriteUseCase: MenuItemFavoriteUseCase
) : ViewModel(),
    IHeaderView,
    IBodyView,
    ILoadingView {

    val uiState = MutableLiveData<MainUiState>()

    // Screen Loading
    private val _loading = MutableLiveData(false)
    override val loadingVisibility: LiveData<Boolean> = _loading

    // Close Button
    override val closeIcon: LiveData<CloseIcon> = MutableLiveData(
        CloseIcon(CloseIconType.CIRCLE_CLOSE, R.color.white)
    )

    // Header & Body
    private val _menuItem = MutableLiveData<MenuItem>()
    override val data: LiveData<MenuItem> = _menuItem

    override val lifecycleOwner: LifecycleOwner
        get() = screenLifecycleOwner

    fun getMenuItem() {
        _loading.value = true
        viewModelScope.launch {
            repository
                .getMenuItem(1)
                .collect { response ->
                    when (response) {
                        is ApiResponse.Success -> {
                            _menuItem.value = response.data
                            _loading.value = !response.sourceType.isRemote()
                        }
                        is ApiResponse.Error -> {
                            _loading.value = false
                            uiState.value = MainUiState.GetMenuItemError
                        }
                    }
                }
        }
    }

    override fun onFavoriteClicked(item: MenuItem) {
        _loading.value = true
        // Switch item favorite
        _menuItem.value = item.switchFavoriteState()
        // Call Api to update
        viewModelScope.launch {
            when (val response = favoriteUseCase.switchFavorite(item)) {
                is ApiResponse.Error -> {
                    _loading.value = false
                    _menuItem.value = _menuItem.value?.switchFavoriteState()
                    uiState.value = MainUiState.FavoriteError(response.getFavoriteException())
                }
                is ApiResponse.Success -> {
                    _loading.value = false
                }
            }
        }
    }
}