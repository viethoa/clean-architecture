package com.hoa.clean_architecture.ui.screen

import com.hoa.clean_architecture.data.entity.MenuItem

sealed interface MainUiState {
    /**
     * Exception when try to get detail of the [MenuItem]
     */
    object GetMenuItemException : MainUiState

    /**
     * Exception when Add item into favorite list or UnFavorite
     */
    object FavoriteException : MainUiState
}