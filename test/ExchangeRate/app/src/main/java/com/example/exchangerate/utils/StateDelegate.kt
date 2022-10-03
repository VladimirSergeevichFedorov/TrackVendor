package com.example.exchangerate.utils

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlin.reflect.KProperty
import kotlin.reflect.KProperty0

internal object DelegateAccess {
    internal val delegate = ThreadLocal<Any?>()
    internal val delegateRequested = ThreadLocal<Boolean>().apply { set(false) }
}

internal val <T> KProperty0<T>.delegate: Any?
    get() {
        try {
            DelegateAccess.delegateRequested.set(true)
            this.get()
            return DelegateAccess.delegate.get()
        } finally {
            DelegateAccess.delegate.set(null)
            DelegateAccess.delegateRequested.set(false)
        }
    }

@Suppress("UNCHECKED_CAST")
val <T> KProperty0<T>.flow: StateFlow<T>
    get() = delegate as StateFlow<T>

class MutableStateDelegate<T> internal constructor(
    private val flow: MutableStateFlow<T>
) : MutableStateFlow<T> by flow {

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        if (DelegateAccess.delegateRequested.get() == true) {
            DelegateAccess.delegate.set(this)
        }
        return flow.value
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        flow.value = value
    }
}

fun <T> state(initialValue: T): MutableStateDelegate<T> {
    return MutableStateDelegate(MutableStateFlow(initialValue))
}