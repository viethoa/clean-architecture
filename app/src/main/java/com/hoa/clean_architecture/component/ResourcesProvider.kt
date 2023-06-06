package com.hoa.clean_architecture.component

import android.content.Context
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import javax.inject.Inject

interface ResourcesProvider {
    /**
     * Get String
     * @param resId resource id of string
     * @return String
     */
    fun getString(@StringRes resId: Int): String

    /**
     * Get String
     * @param formatId resource id of string
     * @param formatArgs format of string
     * @return String
     */
    fun getString(@StringRes formatId: Int, vararg formatArgs: Any): String

    /**
     * Get Color Res ID
     * @param colorId resource id of color
     * @return Int
     */
    fun getColor(@ColorRes colorId: Int): Int
}

class ResourcesProviderImpl constructor(private val context: Context) : ResourcesProvider {

    override fun getString(resId: Int): String {
        return context.getString(resId)
    }

    override fun getString(formatId: Int, vararg formatArgs: Any): String {
        return context.getString(formatId, *formatArgs)
    }

    override fun getColor(colorId: Int): Int {
        return context.getColor(colorId)
    }
}