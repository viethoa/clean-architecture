package com.hoa.clean_architecture.ui.view

import android.view.View
import android.widget.ImageView
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.testing.TestLifecycleOwner
import com.hoa.clean_architecture.R
import com.hoa.clean_architecture.data.entity.MenuItem
import com.hoa.clean_architecture.ui.view.fake.FakeHandler
import com.hoa.clean_architecture.ui.view.favorite.FavoriteIconViewModel
import io.mockk.clearAllMocks
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class FavoriteIconViewModelTest {

    private val rootView: View = mockk(relaxed = true)
    private val favIcon: ImageView = mockk(relaxed = true)
    private val lifecycleOwner: LifecycleOwner = TestLifecycleOwner()

    private val handler = FakeHandler()
    private val viewModel = FavoriteIconViewModel(rootView, lifecycleOwner)

    @Rule
    @JvmField
    val rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        clearAllMocks()
        every { rootView.findViewById<ImageView>(R.id.view_favorite_icon) } returns favIcon
    }

    @Test
    fun `should show as favorite item`() {
        viewModel.initialUI(handler)
        handler.setFavoriteItem()
        assertEquals(viewModel.menuItem, handler.menuItem.value)
        verify(exactly = 1) { favIcon.setImageResource(R.drawable.ic_favorite_filled_red) }
        verify(exactly = 1) { favIcon.setOnClickListener(viewModel) }
        confirmVerified(favIcon)
    }

    @Test
    fun `should show as un-favorite item`() {
        viewModel.initialUI(handler)
        handler.setUnFavoriteItem()
        assertEquals(viewModel.menuItem, handler.menuItem.value)
        verify(exactly = 1) { favIcon.setImageResource(R.drawable.ic_favorite_white) }
        verify(exactly = 1) { favIcon.setOnClickListener(viewModel) }
        confirmVerified(favIcon)
    }

    @Test
    fun `trigger favorite click event with original data`() {
        val expectedItem = MenuItem(
            id = 101,
            name = "name",
            description = "description",
            isFavorite = true
        )
        viewModel.handler = handler
        viewModel.menuItem = expectedItem
        viewModel.onClick(null)
        assertTrue(handler.verifyFavoriteClick(1))
    }
}