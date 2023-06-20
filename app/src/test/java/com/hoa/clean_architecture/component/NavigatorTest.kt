package com.hoa.clean_architecture.component

import android.app.Activity
import io.mockk.clearAllMocks
import io.mockk.confirmVerified
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class NavigatorTest {

    private val activity: Activity = mockk(relaxed = true)

    private val navigator = NavigatorImpl(activity)

    @Before
    fun setup() {
        clearAllMocks()
    }

    @Test
    fun `should exit the screen`() {
        navigator.exitScreen()
        verify(exactly = 1) { activity.finish() }
        confirmVerified(activity)
    }
}