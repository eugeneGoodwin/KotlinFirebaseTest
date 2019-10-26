package com.vortex.soft.kotlinfirebasetest.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseUser
import com.vortex.soft.kotlinfirebasetest.domain.interactor.LogIn

class LoginViewModel (private val logIn: LogIn) : BaseViewModel() {

    var successLogin : MutableLiveData<Boolean> = MutableLiveData()

    fun logIn(email: String, password: String) {
        logIn(scope, LogIn.Params(email, password)) { it.fold(::handleFailure, ::handleLogIn)}
    }

    private fun handleLogIn(fuser : FirebaseUser) {
        this.successLogin.value = true
    }
}