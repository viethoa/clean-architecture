package com.hoa.clean_architecture.ui.base

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.hoa.clean_architecture.ui.bindViewHandler

interface BaseView<T> {
    /**
     * For View to subscribe data within lifecycle awareness
     */
    val lifecycleOwner: LifecycleOwner

    /**
     * Single variable of Screen's ViewModel for all of its Views to subscribe and get data
     */
    val data: LiveData<T>

    fun observeData(function: (T) -> Unit) {
        data.observe(lifecycleOwner) {
            function.invoke(it)
        }
    }
}

/**
 * For BindingAdapter to bind ViewHandler for a SharedView
 * Check [bindViewHandler] for more detail
 */
interface BaseViewHandler

interface BaseSharedView {
    fun bindViewHandler(handler: BaseViewHandler, lifecycleOwner: LifecycleOwner)
}