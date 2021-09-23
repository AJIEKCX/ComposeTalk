package ru.alexpanov.composetalk.ui.login.mvvm

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean

open class SingleLiveData<T> : MutableLiveData<T>() {

    private val isPending = AtomicBoolean(false)

    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        if (hasActiveObservers()) {
            throw IllegalStateException("Multiple observers registered but only one will be notified of changes.")
        }

        super.observe(owner, {
            if (isPending.compareAndSet(true, false)) {
                observer.onChanged(it)
            }
        })
    }

    override fun setValue(value: T?) {
        isPending.set(true)
        super.setValue(value)
    }

    override fun postValue(value: T?) {
        isPending.set(true)
        super.postValue(value)
    }
}