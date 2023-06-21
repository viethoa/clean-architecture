package com.hoa.clean_architecture.ui.base

import androidx.lifecycle.LiveData

interface BaseView<T> {

    /**
     * Single variable of Screen's ViewModel for all of its Views to subscribe and get data
     */
    val data: LiveData<T>
}