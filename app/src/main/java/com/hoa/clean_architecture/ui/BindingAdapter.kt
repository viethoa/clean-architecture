package com.hoa.clean_architecture.ui

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LifecycleOwner
import com.hoa.clean_architecture.ui.base.BaseSharedView
import com.hoa.clean_architecture.ui.base.BaseViewHandler

@BindingAdapter(value = ["bindViewHandler", "bindScreenLifecycle"])
fun bindViewHandler(
    view: View,
    handler: BaseViewHandler,
    lifecycleOwner: LifecycleOwner
) {
    (view as? BaseSharedView)?.bindViewHandler(handler, lifecycleOwner)
}