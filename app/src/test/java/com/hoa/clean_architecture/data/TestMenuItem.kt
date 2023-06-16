package com.hoa.clean_architecture.data

import com.hoa.clean_architecture.data.model.MenuItem
import org.junit.Assert.assertEquals
import org.junit.Test

class TestMenuItem {

    @Test
    fun `switch favorite state`() {
        val item = MenuItem(
            id = 101,
            name = "name",
            description = "description",
            image = 102,
            isFavorite = false
        )
        val expectedData = MenuItem(
            id = 101,
            name = "name",
            description = "description",
            image = 102,
            isFavorite = true
        )
        assertEquals(item.switchFavoriteState(), expectedData)
    }

    @Test
    fun `get valid image`() {
        var item = MenuItem(
            id = 101,
            name = "name",
            description = "description",
            image = 0,
            isFavorite = false
        )
        assertEquals(item.getItemImage(), null)

        item = item.copy(image = null)
        assertEquals(item.getItemImage(), null)

        item = item.copy(image = 101)
        assertEquals(item.getItemImage(), 101)
    }
}