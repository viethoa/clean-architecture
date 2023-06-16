package com.hoa.clean_architecture.data.model

data class MenuItem(
    val id: Int = 0,
    val name: String? = null,
    val image: Int? = null,
    val description: String? = null,
    val isFavorite: Boolean = false
) {
    fun switchFavoriteState(): MenuItem {
        return this.copy(isFavorite = !isFavorite)
    }

    fun getItemImage(): Int? {
        return if (image == null || image == 0) {
            null
        } else {
            image
        }
    }
}