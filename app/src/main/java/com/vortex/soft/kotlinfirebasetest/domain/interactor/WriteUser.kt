package com.vortex.soft.kotlinfirebasetest.domain.interactor

import com.google.firebase.auth.FirebaseUser
import com.vortex.soft.kotlinfirebasetest.data.mapper.UserMapper
import com.vortex.soft.kotlinfirebasetest.data.repository.UserRepositoryInterface
import com.vortex.soft.kotlinfirebasetest.domain.model.DUser

class WriteUser (private val repository: UserRepositoryInterface,
                 private val mapper: UserMapper): UseCase<String, WriteUser.Params>() {
    override suspend fun run(params: Params) = repository.writeUser(params.firebaseUser, mapper.mapToEntity(params.user))

    data class Params(val firebaseUser: FirebaseUser, val user: DUser)
}