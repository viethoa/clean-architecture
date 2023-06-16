package com.hoa.clean_architecture.data.mapper

import com.hoa.clean_architecture.data.local.MenuItemEntity
import com.hoa.clean_architecture.data.model.MenuItem

fun MenuItemEntity.toMenuItem(): MenuItem {
    return MenuItem(
        id = this.id,
        name = this.name,
        image = this.image,
        description = this.description,
        isFavorite = this.isFavorite
    )
}

fun MenuItem.toEntity(): MenuItemEntity {
    return MenuItemEntity(
        id = this.id,
        name = this.name.orEmpty(),
        image = this.image ?: 0,
        description = this.description.orEmpty(),
        isFavorite = this.isFavorite
    )
}