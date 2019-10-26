package com.vortex.soft.kotlinfirebasetest.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vortex.soft.kotlinfirebasetest.data.repository.Error
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren

open class BaseViewModel : ViewModel() {

    private val job = SupervisorJob()
    protected val scope = CoroutineScope(Dispatchers.Default + job)

    var failure : MutableLiveData<Error> = MutableLiveData()

    protected fun handleFailure(fail : Error) {
        this.failure.value = fail
    }

    protected fun cancelAllWork() = scope.coroutineContext.cancelChildren()

    override fun onCleared() {
        cancelAllWork()
        super.onCleared()
    }
}