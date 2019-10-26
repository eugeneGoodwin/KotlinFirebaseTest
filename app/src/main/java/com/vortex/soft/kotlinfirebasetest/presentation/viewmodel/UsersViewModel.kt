package com.vortex.soft.kotlinfirebasetest.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseUser
import com.vortex.soft.kotlinfirebasetest.domain.interactor.GetUsers
import com.vortex.soft.kotlinfirebasetest.domain.interactor.Register
import com.vortex.soft.kotlinfirebasetest.domain.interactor.UseCase
import com.vortex.soft.kotlinfirebasetest.domain.interactor.WriteUser
import com.vortex.soft.kotlinfirebasetest.domain.model.DUser

class UsersViewModel (private val getUsers : GetUsers,
                      private val register : Register,
                      private val writeUser: WriteUser) : BaseViewModel() {

    var users : MutableLiveData<List<DUser>> = MutableLiveData()
    var user: DUser? = null

    fun loadUsers() = getUsers(scope, UseCase.None()) { it.fold(::handleFailure, ::handleUsersList)}

    private fun handleUsersList(dusers : List<DUser>) {
        this.users.value = dusers
    }

    fun register(email: String, password: String) = register(scope, Register.Params(email, password)) { it.fold(::handleFailure, ::handleRegister) }

    private fun handleRegister(fuser : FirebaseUser) {
        user?.let {  writeUser(scope, WriteUser.Params(fuser, it)){ it.fold(::handleFailure, ::handleWriteUser) } }
    }

    private fun handleWriteUser(key: String) {
        loadUsers()
    }
}