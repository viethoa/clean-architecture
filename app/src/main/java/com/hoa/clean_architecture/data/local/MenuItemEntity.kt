package com.hoa.clean_architecture.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "menu_item"
)
data class MenuItemEntity(
    @PrimaryKey
    @ColumnInfo(name = "item_id")
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "image")
    val image: Int,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "isFavorite")
    val isFavorite: Boolean
)