package com.vortex.soft.kotlinfirebasetest.domain.interactor

import com.vortex.soft.kotlinfirebasetest.data.repository.UserRepositoryInterface
import com.vortex.soft.kotlinfirebasetest.domain.model.DUser

class GetUsersWithKeys (private val repository: UserRepositoryInterface) : UseCase<Map<String, DUser>,UseCase.None>() {
    override suspend fun run(params: None) = repository.usersWithKeys()
}