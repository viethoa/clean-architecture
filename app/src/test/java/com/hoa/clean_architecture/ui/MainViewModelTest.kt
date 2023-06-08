package com.hoa.clean_architecture.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.hoa.clean_architecture.data.ApiResponse
import com.hoa.clean_architecture.data.MenuRepository
import com.hoa.clean_architecture.data.entity.MenuItem
import com.hoa.clean_architecture.domain.MenuItemFavoriteUseCase
import com.hoa.clean_architecture.ui.screen.MainUiState
import com.hoa.clean_architecture.ui.screen.MainViewModel
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModelTest {

    private val repository: MenuRepository = mockk(relaxed = true)
    private val favoriteUseCase: MenuItemFavoriteUseCase = mockk(relaxed = true)

    private val dispatcher: TestDispatcher = UnconfinedTestDispatcher()
    private val viewModel = MainViewModel(repository, favoriteUseCase)

    @Rule
    @JvmField
    val rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        clearAllMocks()
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `fetch menu item detail success`() {
        val response = MenuItem(
            id = 101,
            name = "name",
            description = "description",
            image = 102,
            isFavorite = false
        )
        coEvery { repository.getMenuItem() } returns ApiResponse.Success(response)
        viewModel.getMenuItem()
        assertEquals(viewModel.loadingVisibility.value, false)
        assertEquals(viewModel.menuItem.value, response)
        assertEquals(viewModel.uiState.value, null)
        coVerify(exactly = 1) { repository.getMenuItem() }
        confirmVerified(repository)
    }

    @Test
    fun `fetch menu item detail error`() {
        val response = Exception("Something went wrong")
        coEvery { repository.getMenuItem() } returns ApiResponse.Error(response)
        viewModel.getMenuItem()
        assertEquals(viewModel.loadingVisibility.value, false)
        assertEquals(viewModel.uiState.value, MainUiState.GetMenuItemException)
        assertEquals(viewModel.menuItem.value, null)
        coVerify(exactly = 1) { repository.getMenuItem() }
        confirmVerified(repository)
    }

    @Test
    fun `Add item into favorite success`() {
        val item = MenuItem(
            id = 101,
            name = "name",
            description = "description",
            image = 102,
            isFavorite = false
        )
        coEvery { favoriteUseCase.switchFavorite(item) } returns ApiResponse.Success(Any())
        viewModel.onFavoriteClicked(item)
        assertEquals(viewModel.uiState.value, null)
        assertEquals(viewModel.loadingVisibility.value, false)
        assertEquals(viewModel.menuItem.value, item.switchFavoriteState())
        coVerify(exactly = 1) { favoriteUseCase.switchFavorite(item) }
        confirmVerified(favoriteUseCase)
        confirmVerified(repository)
    }

    @Test
    fun `Add item into favorite error`() {
        val item = MenuItem(
            id = 101,
            name = "name",
            description = "description",
            image = 102,
            isFavorite = false
        )
        val exception = Exception("Something went wrong")
        coEvery { favoriteUseCase.switchFavorite(item) } returns ApiResponse.Error(exception)
        viewModel.onFavoriteClicked(item)
        assertEquals(viewModel.menuItem.value, item)
        assertEquals(viewModel.loadingVisibility.value, false)
        assertEquals(viewModel.uiState.value, MainUiState.FavoriteException)
        coVerify(exactly = 1) { favoriteUseCase.switchFavorite(item) }
        confirmVerified(favoriteUseCase)
        confirmVerified(repository)
    }

    @Test
    fun `UnFavorite item success`() {
        val item = MenuItem(
            id = 101,
            name = "name",
            description = "description",
            image = 102,
            isFavorite = true
        )
        coEvery { favoriteUseCase.switchFavorite(item) } returns ApiResponse.Success(Any())
        viewModel.onFavoriteClicked(item)
        assertEquals(viewModel.uiState.value, null)
        assertEquals(viewModel.loadingVisibility.value, false)
        assertEquals(viewModel.menuItem.value, item.switchFavoriteState())
        coVerify(exactly = 1) { favoriteUseCase.switchFavorite(item) }
        confirmVerified(favoriteUseCase)
        confirmVerified(repository)
    }

    @Test
    fun `UnFavorite item error`() {
        val item = MenuItem(
            id = 101,
            name = "name",
            description = "description",
            image = 102,
            isFavorite = true
        )
        val exception = Exception("Something went wrong")
        coEvery { favoriteUseCase.switchFavorite(item) } returns ApiResponse.Error(exception)
        viewModel.onFavoriteClicked(item)
        assertEquals(viewModel.menuItem.value, item)
        assertEquals(viewModel.loadingVisibility.value, false)
        assertEquals(viewModel.uiState.value, MainUiState.FavoriteException)
        coVerify(exactly = 1) { favoriteUseCase.switchFavorite(item) }
        confirmVerified(favoriteUseCase)
        confirmVerified(repository)
    }
}