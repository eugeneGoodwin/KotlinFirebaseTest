package com.vortex.soft.kotlinfirebasetest.domain.interactor

import com.google.firebase.auth.FirebaseUser
import com.vortex.soft.kotlinfirebasetest.data.repository.UserRepositoryInterface

class LogIn (private val repository: UserRepositoryInterface) : UseCase<FirebaseUser, LogIn.Params>() {
    override suspend fun run(params: Params) = repository.logIn(params.email, params.password)
    data class Params(val email: String, val password: String)
}