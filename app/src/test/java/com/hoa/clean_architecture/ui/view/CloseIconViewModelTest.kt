package com.hoa.clean_architecture.ui.view

import android.view.View
import android.widget.ImageView
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.testing.TestLifecycleOwner
import com.hoa.clean_architecture.R
import com.hoa.clean_architecture.component.Navigator
import com.hoa.clean_architecture.component.ResourcesProvider
import com.hoa.clean_architecture.ui.view.closeicon.CloseIconViewModel
import com.hoa.clean_architecture.ui.fake.FakeHandler
import io.mockk.clearAllMocks
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class CloseIconViewModelTest {

    private val rootView: View = mockk(relaxed = true)
    private val closeButton: ImageView = mockk(relaxed = true)
    private val navigator: Navigator = mockk(relaxed = true)
    private val resourcesProvider: ResourcesProvider = mockk(relaxed = true)
    private val lifecycleOwner: LifecycleOwner = TestLifecycleOwner()
    private val handler = FakeHandler()

    private val viewModel = CloseIconViewModel(
        rootView,
        navigator,
        lifecycleOwner,
        resourcesProvider
    )

    @Rule
    @JvmField
    val rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        clearAllMocks()
        every { rootView.findViewById<ImageView>(R.id.view_close_button) } returns closeButton
        every { resourcesProvider.getColor(R.color.red) } returns 1010
        every { resourcesProvider.getColor(R.color.bluish) } returns 1011
    }

    @Test
    fun `config as red circle close icon`() {
        viewModel.initializeUI(handler)
        handler.setRedCircleCloseIcon()
        verify(exactly = 1) { resourcesProvider.getColor(R.color.red) }
        verify(exactly = 1) { closeButton.setImageResource(R.drawable.ic_close_circle_white) }
        verify(exactly = 1) { closeButton.setColorFilter(1010) }
        verify(exactly = 1) { closeButton.setOnClickListener(viewModel) }
        confirmVerified(closeButton)
        confirmVerified(resourcesProvider)
    }

    @Test
    fun `config as bluish close icon`() {
        viewModel.initializeUI(handler)
        handler.setBluishCloseIcon()
        verify(exactly = 1) { resourcesProvider.getColor(R.color.bluish) }
        verify(exactly = 1) { closeButton.setImageResource(R.drawable.ic_close_white) }
        verify(exactly = 1) { closeButton.setColorFilter(1011) }
        verify(exactly = 1) { closeButton.setOnClickListener(viewModel) }
        confirmVerified(closeButton)
        confirmVerified(resourcesProvider)
    }

    @Test
    fun `exit screen when click close button`() {
        viewModel.onClick(null)
        verify(exactly = 1) { navigator.exitScreen() }
        confirmVerified(navigator)
    }
}