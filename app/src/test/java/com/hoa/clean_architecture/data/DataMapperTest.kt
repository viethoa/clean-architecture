package com.hoa.clean_architecture.data

import com.hoa.clean_architecture.data.local.MenuItemEntity
import com.hoa.clean_architecture.data.mapper.toEntity
import com.hoa.clean_architecture.data.mapper.toMenuItem
import com.hoa.clean_architecture.data.model.MenuItem
import org.junit.Assert.assertEquals
import org.junit.Test

class DataMapperTest {

    @Test
    fun `convert MenuItemEntity to MenuItem`() {
        val entity = MenuItemEntity(
            id = 101,
            name = "name",
            image = 102,
            description = "description",
            isFavorite = true
        )
        val expected = MenuItem(
            id = 101,
            name = "name",
            image = 102,
            description = "description",
            isFavorite = true
        )
        assertEquals(entity.toMenuItem(), expected)
    }

    @Test
    fun `convert MenuItem to MenuItemEntity`() {
        val menuItem = MenuItem(
            id = 101,
            name = "name",
            image = 102,
            description = "description",
            isFavorite = true
        )
        val expected = MenuItemEntity(
            id = 101,
            name = "name",
            image = 102,
            description = "description",
            isFavorite = true
        )
        assertEquals(menuItem.toEntity(), expected)
    }
}