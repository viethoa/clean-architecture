package com.hoa.clean_architecture.ui.extension

import android.app.Activity
import android.content.Context
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import com.hoa.clean_architecture.ui.base.DIParent
import kotlin.reflect.KProperty

/**
 * A view belong to an Activity can share its parent dependency by [DIParent.getDependency]
 */
val View.asDIParent: DIParent?
    get() = ((context as? Activity) as? DIParent)

/**
 * Custom View Lifecycle Owner
 * - State when created [Lifecycle.State.CREATED]
 * - State when attached to window [Lifecycle.State.RESUMED]
 * - State when detached from window [Lifecycle.State.DESTROYED]
 */
fun View.customViewLifeCycleOwner() = ViewLifecycleDelegate(this)

class ViewLifecycleDelegate(view: View) : LifecycleOwner, View.OnAttachStateChangeListener {

    private val lifecycleRegistry = LifecycleRegistry(this)

    init {
        lifecycleRegistry.currentState = Lifecycle.State.CREATED
        view.addOnAttachStateChangeListener(this)
    }

    override fun onViewAttachedToWindow(v: View) {
        lifecycleRegistry.currentState = Lifecycle.State.STARTED
        lifecycleRegistry.currentState = Lifecycle.State.RESUMED
    }

    override fun onViewDetachedFromWindow(v: View) {
        lifecycleRegistry.currentState = Lifecycle.State.DESTROYED
        v.removeOnAttachStateChangeListener(this)
    }

    override val lifecycle: Lifecycle = lifecycleRegistry

    operator fun getValue(view: View, property: KProperty<*>): LifecycleOwner {
        return this
    }
}