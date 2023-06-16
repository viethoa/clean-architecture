package com.hoa.clean_architecture.domain

import com.hoa.clean_architecture.data.ApiException
import com.hoa.clean_architecture.data.ApiResponse
import com.hoa.clean_architecture.domain.mapper.getFavoriteException
import com.hoa.clean_architecture.domain.usecase.FAKE_FAVORITE_EXCEPTION
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.lang.ClassCastException

class DomainDataMapperTest {

    @Test
    fun `can get favorite error message`() {
        assertEquals(
            "Favorite Exception",
            ApiResponse.Error<Any>(FAKE_FAVORITE_EXCEPTION).getFavoriteException()
        )
    }

    @Test
    fun `should through ClassCastException for wrong Api Error Type`() {
        val exception = ClassCastException(
            "The ApiException is not type of FavoriteException, please check your logic"
        )
        // Throw error for ApiResponse.SuccessType
        try {
            ApiResponse.Success(Any()).getFavoriteException()
        } catch (throwable: Throwable) {
            assertTrue(throwable is ClassCastException)
            assertEquals(throwable.message, exception.message)
        }
        // Throw error for wrong ApiResponse.Error
        try {
            ApiResponse.Error<Any>(ApiException()).getFavoriteException()
        } catch (throwable: Throwable) {
            assertTrue(throwable is ClassCastException)
            assertEquals(throwable.message, exception.message)
        }
    }
}