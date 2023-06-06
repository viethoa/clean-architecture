package com.hoa.clean_architecture.ui.screen.body

import androidx.lifecycle.LiveData
import com.hoa.clean_architecture.data.entity.MenuItem

interface IBodyView {
    /**
     * MainActivity Body Content Data
     */
    val bodyContent: LiveData<MenuItem>
}