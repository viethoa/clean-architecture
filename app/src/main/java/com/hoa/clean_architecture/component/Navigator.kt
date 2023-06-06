package com.hoa.clean_architecture.component

import android.app.Activity

interface Navigator {
    /**
     * Exit the current screen
     */
    fun exitScreen()
}

class NavigatorImpl(private val activity: Activity) : Navigator {

    override fun exitScreen() {
        activity.finish()
    }
}

