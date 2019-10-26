package com.vortex.soft.kotlinfirebasetest.domain.interactor

import com.google.firebase.auth.FirebaseUser
import com.vortex.soft.kotlinfirebasetest.data.repository.UserRepositoryInterface

class Register (private val repository: UserRepositoryInterface) : UseCase<FirebaseUser, Register.Params>() {
    override suspend fun run(params: Params) = repository.register(params.email, params.password)
    data class Params(val email: String, val password: String)
}