package com.hoa.clean_architecture.domain

import com.hoa.clean_architecture.data.ApiException
import com.hoa.clean_architecture.data.ApiResponse
import com.hoa.clean_architecture.data.repository.MenuItemRepository
import com.hoa.clean_architecture.data.model.MenuItem
import com.hoa.clean_architecture.domain.usecase.FAKE_FAVORITE_EXCEPTION
import com.hoa.clean_architecture.domain.usecase.MenuItemFavoriteUseCaseImpl
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class FavoriteUseCaseTest {

    private val menuRepository: MenuItemRepository = mockk(relaxed = true)

    private val useCase = MenuItemFavoriteUseCaseImpl(menuRepository)

    @Before
    fun setup() {
        clearAllMocks()
    }

    @Test
    fun `un-favorite an item successful`() = runTest {
        val item = MenuItem(
            id = 101,
            name = "name",
            isFavorite = true
        )
        val response = ApiResponse.Success(Any())
        coEvery { menuRepository.unFavorite(item) } returns response

        assertEquals(useCase.switchFavorite(item), response)
        coVerify(exactly = 1) { menuRepository.unFavorite(item) }
        confirmVerified(menuRepository)
    }

    @Test
    fun `un-favorite an item error`() = runTest {
        val item = MenuItem(
            id = 101,
            name = "name",
            isFavorite = true
        )
        val expectedResponse = ApiResponse.Error<ApiResponse<Any>>(
            FAKE_FAVORITE_EXCEPTION
        )
        val response = ApiResponse.Error<ApiResponse<Any>>(
            ApiException("Something went wrong")
        )
        coEvery { menuRepository.unFavorite(item) } returns response

        assertEquals(useCase.switchFavorite(item), expectedResponse)
        coVerify(exactly = 1) { menuRepository.unFavorite(item) }
        confirmVerified(menuRepository)
    }

    @Test
    fun `add item into favorite successful`() = runTest {
        val item = MenuItem(
            id = 102,
            name = "name",
            isFavorite = false
        )
        val response = ApiResponse.Success(Any())
        coEvery { menuRepository.addItemToFavorite(item) } returns response

        assertEquals(useCase.switchFavorite(item), response)
        coVerify(exactly = 1) { menuRepository.addItemToFavorite(item) }
        confirmVerified(menuRepository)
    }

    @Test
    fun `add item into favorite error`() = runTest {
        val item = MenuItem(
            id = 102,
            name = "name",
            isFavorite = false
        )
        val expectedResponse = ApiResponse.Error<ApiResponse<Any>>(
            FAKE_FAVORITE_EXCEPTION
        )
        val response = ApiResponse.Error<ApiResponse<Any>>(
            ApiException("Something went wrong")
        )
        coEvery { menuRepository.addItemToFavorite(item) } returns response

        assertEquals(useCase.switchFavorite(item), expectedResponse)
        coVerify(exactly = 1) { menuRepository.addItemToFavorite(item) }
        confirmVerified(menuRepository)
    }
}