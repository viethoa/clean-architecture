package com.hoa.clean_architecture.ui.view

import android.view.View
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.testing.TestLifecycleOwner
import com.hoa.clean_architecture.R
import com.hoa.clean_architecture.ui.fake.FakeHandler
import com.hoa.clean_architecture.ui.view.loading.LoadingViewModel
import io.mockk.clearAllMocks
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class LoadingViewModelTest {

    private val rootView: View = mockk(relaxed = true)
    private val loadingView: View = mockk(relaxed = true)
    private val lifecycleOwner: LifecycleOwner = TestLifecycleOwner()

    private val handler = FakeHandler()
    private val viewModel = LoadingViewModel(rootView, lifecycleOwner)

    @Rule
    @JvmField
    val rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        clearAllMocks()
        every { rootView.findViewById<View>(R.id.loading_progress_bar) } returns loadingView
    }

    @Test
    fun `show loading view`() {
        viewModel.initializeUI(handler)
        handler.showLoading()
        verify(exactly = 1) { loadingView.visibility = View.VISIBLE }
        confirmVerified(loadingView)
    }

    @Test
    fun `hide loading view`() {
        viewModel.initializeUI(handler)
        handler.hideLoading()
        verify(exactly = 1) { loadingView.visibility = View.GONE }
        confirmVerified(loadingView)
    }
}