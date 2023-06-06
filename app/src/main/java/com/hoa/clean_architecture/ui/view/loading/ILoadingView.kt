package com.hoa.clean_architecture.ui.view.loading

import androidx.lifecycle.LiveData

interface ILoadingView {
    /**
     * - True: show loading
     * - False: hide loading
     */
    val loadingVisibility: LiveData<Boolean>
}