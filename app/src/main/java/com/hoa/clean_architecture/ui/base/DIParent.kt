package com.hoa.clean_architecture.ui.base

import kotlin.reflect.KClass

interface DIParent {
    fun <T : Any> getDependency(cls: KClass<T>): T
}