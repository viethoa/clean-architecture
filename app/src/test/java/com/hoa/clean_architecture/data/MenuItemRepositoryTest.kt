package com.hoa.clean_architecture.data

import com.hoa.clean_architecture.data.local.MenuItemDao
import com.hoa.clean_architecture.data.local.MenuItemEntity
import com.hoa.clean_architecture.data.mapper.toEntity
import com.hoa.clean_architecture.data.mapper.toMenuItem
import com.hoa.clean_architecture.data.model.MenuItem
import com.hoa.clean_architecture.data.remote.MenuItemApiService
import com.hoa.clean_architecture.data.repository.MenuItemRepositoryImpl
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MenuItemRepositoryTest {

    private val menuItemDao: MenuItemDao = mockk(relaxed = true)
    private val menuItemApiService: MenuItemApiService = mockk(relaxed = true)

    private val repository = MenuItemRepositoryImpl(menuItemDao, menuItemApiService)

    @Before
    fun setup() {
        clearAllMocks()
    }

    @Test
    fun `get menu item with only remote data and no local data`() = runTest {
        val menuItemId = 101
        val menuItem = MenuItem(
            id = menuItemId,
            name = "name",
            description = "description",
            image = 102,
            isFavorite = false
        )
        val remoteMenuItem = ApiResponse.Success(menuItem)
        coEvery { menuItemDao.getMenuItem(menuItemId) } returns null
        coEvery { menuItemApiService.getMenuItem(menuItemId) } returns remoteMenuItem
        val response = repository
            .getMenuItem(menuItemId)
            .toList()

        assertEquals(response, listOf(remoteMenuItem))
        coVerify (exactly = 1) { menuItemDao.getMenuItem(menuItemId) }
        coVerify (exactly = 1) { menuItemApiService.getMenuItem(menuItemId) }
        coVerify (exactly = 1) { menuItemDao.addMenuItem(menuItem.toEntity()) }
        confirmVerified(menuItemApiService)
        confirmVerified(menuItemDao)
    }

    @Test
    fun `get menu item with local and remote data`() = runTest {
        val menuItemId = 101
        val menuItem = MenuItem(
            id = menuItemId,
            name = "name",
            description = "description",
            image = 102,
            isFavorite = false
        )
        val menuItemEntity = MenuItemEntity(
            id = menuItemId,
            name = "name",
            description = "description",
            image = 102,
            isFavorite = false
        )
        val remoteMenuItem = ApiResponse.Success(menuItem)
        val localMenuItem = ApiResponse.Success(menuItemEntity.toMenuItem(), SourceType.LOCAL)
        coEvery { menuItemDao.getMenuItem(menuItemId) } returns menuItemEntity
        coEvery { menuItemApiService.getMenuItem(menuItemId) } returns remoteMenuItem
        val response = repository
            .getMenuItem(menuItemId)
            .toList()

        assertEquals(response, listOf(localMenuItem, remoteMenuItem))
        coVerify (exactly = 1) { menuItemDao.getMenuItem(menuItemId) }
        coVerify (exactly = 1) { menuItemApiService.getMenuItem(menuItemId) }
        coVerify (exactly = 1) { menuItemDao.addMenuItem(menuItem.toEntity()) }
        confirmVerified(menuItemApiService)
        confirmVerified(menuItemDao)
    }

    @Test
    fun `get menu item exception and no local data`() = runTest {
        val menuItemId = 101
        val exception = ApiException("Test")
        coEvery { menuItemDao.getMenuItem(menuItemId) } returns null
        coEvery { menuItemApiService.getMenuItem(menuItemId) } returns ApiResponse.Error(exception)
        val response = repository
            .getMenuItem(menuItemId)
            .toList()

        assertEquals(response, listOf<ApiResponse<MenuItem>>(ApiResponse.Error(exception)))
        coVerify (exactly = 1) { menuItemDao.getMenuItem(menuItemId) }
        coVerify (exactly = 1) { menuItemApiService.getMenuItem(menuItemId) }
        confirmVerified(menuItemApiService)
        confirmVerified(menuItemDao)
    }

    @Test
    fun `get menu item exception and have local data`() = runTest {
        val menuItemId = 101
        val menuItemEntity = MenuItemEntity(
            id = menuItemId,
            name = "name",
            description = "description",
            image = 102,
            isFavorite = false
        )
        val exception = ApiException("Test")
        val localMenuItem = ApiResponse.Success(menuItemEntity.toMenuItem(), SourceType.LOCAL)
        coEvery { menuItemDao.getMenuItem(menuItemId) } returns menuItemEntity
        coEvery { menuItemApiService.getMenuItem(menuItemId) } returns ApiResponse.Error(exception)
        val response = repository
            .getMenuItem(menuItemId)
            .toList()

        assertEquals(response, listOf(localMenuItem, ApiResponse.Error(exception)))
        coVerify (exactly = 1) { menuItemDao.getMenuItem(menuItemId) }
        coVerify (exactly = 1) { menuItemApiService.getMenuItem(menuItemId) }
        confirmVerified(menuItemApiService)
        confirmVerified(menuItemDao)
    }

    @Test
    fun `add item into favorite successful`() = runTest {
        val menuItem = MenuItem(
            id = 101,
            name = "name",
            description = "description",
            image = 102,
            isFavorite = false
        )
        val expectedResponse = ApiResponse.Success(Any())
        coEvery { menuItemApiService.addItemToFavorite(menuItem) } returns expectedResponse

        assertEquals(repository.addItemToFavorite(menuItem), expectedResponse)
        coVerify(exactly = 1) { menuItemApiService.addItemToFavorite(menuItem) }
        confirmVerified(menuItemApiService)
        confirmVerified(menuItemDao)
    }

    @Test
    fun `add item into favorite error`() = runTest {
        val menuItem = MenuItem(
            id = 101,
            name = "name",
            description = "description",
            image = 102,
            isFavorite = false
        )
        val expectedResponse = ApiResponse.Error<ApiResponse<Any>>(ApiException("Test"))
        coEvery { menuItemApiService.addItemToFavorite(menuItem) } returns expectedResponse

        assertEquals(repository.addItemToFavorite(menuItem), expectedResponse)
        coVerify(exactly = 1) { menuItemApiService.addItemToFavorite(menuItem) }
        confirmVerified(menuItemApiService)
        confirmVerified(menuItemDao)
    }

    @Test
    fun `Remove item from favorite success`() = runTest {
        val menuItem = MenuItem(
            id = 101,
            name = "name",
            description = "description",
            image = 102,
            isFavorite = true
        )
        val expectedResponse = ApiResponse.Success(Any())
        coEvery { menuItemApiService.unFavorite(menuItem) } returns expectedResponse

        assertEquals(repository.unFavorite(menuItem), expectedResponse)
        coVerify(exactly = 1) { menuItemApiService.unFavorite(menuItem) }
        confirmVerified(menuItemApiService)
        confirmVerified(menuItemDao)
    }

    @Test
    fun `Remove item from favorite error`() = runTest {
        val menuItem = MenuItem(
            id = 101,
            name = "name",
            description = "description",
            image = 102,
            isFavorite = true
        )
        val expectedResponse = ApiResponse.Error<ApiResponse<Any>>(ApiException("Test"))
        coEvery { menuItemApiService.unFavorite(menuItem) } returns expectedResponse

        assertEquals(repository.unFavorite(menuItem), expectedResponse)
        coVerify(exactly = 1) { menuItemApiService.unFavorite(menuItem) }
        confirmVerified(menuItemApiService)
        confirmVerified(menuItemDao)
    }
}