package com.hoa.clean_architecture.ui.extension

import android.app.Activity
import android.view.View
import com.hoa.clean_architecture.ui.base.DIParent

/**
 * A view belong to an Activity can share its parent dependency by [DIParent.getDependency]
 */
val View.asDIParent: DIParent?
    get() = ((context as? Activity) as? DIParent)