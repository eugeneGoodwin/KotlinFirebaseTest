package com.vortex.soft.kotlinfirebasetest.domain.interactor

import com.vortex.soft.kotlinfirebasetest.data.repository.UserRepositoryInterface
import com.vortex.soft.kotlinfirebasetest.domain.model.DUser

class GetUsers (private val repository: UserRepositoryInterface) : UseCase<List<DUser>,UseCase.None>() {
    override suspend fun run(params: None) = repository.users()
}