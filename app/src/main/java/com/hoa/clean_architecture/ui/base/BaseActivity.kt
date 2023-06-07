package com.hoa.clean_architecture.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlin.reflect.KClass

abstract class BaseActivity : AppCompatActivity(), DIParent {

    abstract fun setupDependencyInjection()

    abstract fun getActivityComponent(): Any

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupDependencyInjection()
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : Any> getDependency(cls: KClass<T>): T {
        if (cls.isInstance(getActivityComponent())) {
            return getActivityComponent() as T
        } else {
            throw Exception("Update MainComponent to extend from your ${cls.simpleName}")
        }
    }
}