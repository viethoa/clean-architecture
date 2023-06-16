package com.hoa.clean_architecture.component

import android.content.Context
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ResourcesProviderTest {

    private val context: Context = mockk(relaxed = true)

    private val resourcesProvider = ResourcesProviderImpl(context)

    @Before
    fun setup() {
        clearAllMocks()
    }

    @Test
    fun `get string resource`() {
        val stringRes = 101
        val someText = "some text"
        every { context.getString(stringRes) } returns someText
        assertEquals(someText, resourcesProvider.getString(stringRes))
    }

    @Test
    fun `get color resource`() {
        val colorId = 101
        val myColor = 102
        every { context.getColor(colorId) } returns myColor
        assertEquals(myColor, resourcesProvider.getColor(colorId))
    }
}