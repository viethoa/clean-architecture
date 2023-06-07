package com.hoa.clean_architecture.domain

import com.hoa.clean_architecture.data.MenuRepository
import com.hoa.clean_architecture.data.entity.MenuItem
import io.mockk.clearAllMocks
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class FavoriteUseCaseTest {

    private val menuRepository: MenuRepository = mockk(relaxed = true)

    private val useCase = MenuItemFavoriteUseCaseImpl(menuRepository)

    @Before
    fun setup() {
        clearAllMocks()
    }

    @Test
    fun `un-favorite an item`() {
        val item = MenuItem(
            id = 101,
            name = "name",
            isFavorite = true
        )
        runBlocking { useCase.switchFavorite(item) }
        coVerify(exactly = 1) { menuRepository.unFavorite(item) }
        confirmVerified(menuRepository)
    }

    @Test
    fun `add item into favorite`() {
        val item = MenuItem(
            id = 102,
            name = "name",
            isFavorite = false
        )
        runBlocking { useCase.switchFavorite(item) }
        coVerify(exactly = 1) { menuRepository.addItemToFavorite(item) }
        confirmVerified(menuRepository)
    }
}