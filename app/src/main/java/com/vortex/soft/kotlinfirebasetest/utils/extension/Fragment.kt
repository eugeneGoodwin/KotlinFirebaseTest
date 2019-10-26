package com.vortex.soft.kotlinfirebasetest.utils.extension

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel

inline fun <reified T : ViewModel> Fragment.viewModel(model: T, body: T.() -> Unit): T {
    model.body()
    return model
}

inline fun <reified T : ViewModel> Activity.viewModel(model: T, body: T.() -> Unit): T {
    model.body()
    return model
}

fun <T> Fragment.observe(liveData: LiveData<T>, action: (t: T?) -> Unit) {
    liveData.observe(viewLifecycleOwner, Observer { it?.let{ action(it) } })
}