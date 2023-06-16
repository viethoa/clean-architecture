package com.hoa.clean_architecture.ui.screen

import com.hoa.clean_architecture.data.model.MenuItem

sealed class MainUiState {
    /**
     * Exception when try to get detail of the [MenuItem]
     */
    object GetMenuItemError : MainUiState()

    /**
     * Exception when Add item into user favorite list or Remove item from user favorite list
     */
    data class FavoriteError(val error: String) : MainUiState()
}