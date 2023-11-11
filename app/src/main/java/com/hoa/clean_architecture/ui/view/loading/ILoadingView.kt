package com.hoa.clean_architecture.ui.view.loading

import androidx.lifecycle.LiveData
import com.hoa.clean_architecture.ui.base.BaseViewHandler

interface ILoadingView : BaseViewHandler {
    /**
     * - True: show loading
     * - False: hide loading
     */
    val loadingVisibility: LiveData<Boolean>
}