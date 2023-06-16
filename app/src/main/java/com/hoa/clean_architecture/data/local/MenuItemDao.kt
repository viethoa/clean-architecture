package com.hoa.clean_architecture.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MenuItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMenuItem(insight: MenuItemEntity): Long

    @Query("SELECT * FROM menu_item WHERE item_id=:itemId")
    suspend fun getMenuItem(itemId: Int): MenuItemEntity?
}