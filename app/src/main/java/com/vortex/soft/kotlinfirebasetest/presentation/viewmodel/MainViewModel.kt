package com.vortex.soft.kotlinfirebasetest.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import com.vortex.soft.kotlinfirebasetest.domain.interactor.IsLogged
import com.vortex.soft.kotlinfirebasetest.domain.interactor.LogOut
import com.vortex.soft.kotlinfirebasetest.domain.interactor.UseCase

class MainViewModel (private val isLogged: IsLogged, private val logOut: LogOut) : BaseViewModel() {

    var isUserLogged : MutableLiveData<Boolean> = MutableLiveData()
    var isLogOut : MutableLiveData<Boolean> = MutableLiveData()

    fun checkSession() {
        isLogged(scope, UseCase.None()) { it.fold(::handleFailure, ::handleIsLogged)}
    }

    fun logOut() {
        logOut(scope, UseCase.None()) { it.fold(::handleFailure, ::handleLogOut)}
    }

    private fun handleLogOut(none: UseCase.None) {
        this.isLogOut.value = true
    }

    private fun handleIsLogged(isLoged : Boolean) {
        this.isUserLogged.value = isLoged
    }
}