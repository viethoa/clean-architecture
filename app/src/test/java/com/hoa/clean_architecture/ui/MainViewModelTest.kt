package com.hoa.clean_architecture.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.hoa.clean_architecture.data.ApiException
import com.hoa.clean_architecture.data.ApiResponse
import com.hoa.clean_architecture.data.SourceType
import com.hoa.clean_architecture.data.repository.MenuItemRepository
import com.hoa.clean_architecture.data.model.MenuItem
import com.hoa.clean_architecture.domain.model.FavoriteException
import com.hoa.clean_architecture.domain.usecase.MenuItemFavoriteUseCase
import com.hoa.clean_architecture.ui.fake.FakeMenuRepository
import com.hoa.clean_architecture.ui.screen.MainUiState
import com.hoa.clean_architecture.ui.screen.MainViewModel
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
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

    private val favoriteUseCase: MenuItemFavoriteUseCase = mockk(relaxed = true)
    private val repository: MenuItemRepository = mockk(relaxed = true)

    private val dispatcher: TestDispatcher = UnconfinedTestDispatcher()
    private var viewModel = MainViewModel(repository, favoriteUseCase)

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
        val menuItem = MenuItem(
            id = 101,
            name = "name",
            description = "description",
            image = 102,
            isFavorite = false
        )
        val fakeRepository = FakeMenuRepository(ApiResponse.Success(menuItem, SourceType.REMOTE))
        viewModel = MainViewModel(fakeRepository, favoriteUseCase)
        viewModel.getMenuItem()
        assertEquals(viewModel.loadingVisibility.value, false)
        assertEquals(viewModel.data.value, menuItem)
        assertEquals(viewModel.uiState.value, null)
    }

    @Test
    fun `fetch menu item detail error`() {
        val response = ApiException("Something went wrong")
        val fakeRepository = FakeMenuRepository(ApiResponse.Error(response))
        viewModel = MainViewModel(fakeRepository, favoriteUseCase)
        viewModel.getMenuItem()
        assertEquals(viewModel.loadingVisibility.value, false)
        assertEquals(viewModel.uiState.value, MainUiState.GetMenuItemError)
        assertEquals(viewModel.data.value, null)
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
        assertEquals(viewModel.data.value, item.switchFavoriteState())
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
        val exception = FavoriteException("Something went wrong")
        coEvery { favoriteUseCase.switchFavorite(item) } returns ApiResponse.Error(exception)
        viewModel.onFavoriteClicked(item)
        assertEquals(viewModel.data.value, item)
        assertEquals(viewModel.loadingVisibility.value, false)
        assertEquals(viewModel.uiState.value, MainUiState.FavoriteError("Something went wrong"))
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
        assertEquals(viewModel.data.value, item.switchFavoriteState())
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
        val exception = FavoriteException("Something went wrong")
        coEvery { favoriteUseCase.switchFavorite(item) } returns ApiResponse.Error(exception)
        viewModel.onFavoriteClicked(item)
        assertEquals(viewModel.data.value, item)
        assertEquals(viewModel.loadingVisibility.value, false)
        assertEquals(viewModel.uiState.value, MainUiState.FavoriteError("Something went wrong"))
        coVerify(exactly = 1) { favoriteUseCase.switchFavorite(item) }
        confirmVerified(favoriteUseCase)
        confirmVerified(repository)
    }
}