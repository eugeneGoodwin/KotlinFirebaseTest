package com.vortex.soft.kotlinfirebasetest.domain.interactor

import com.vortex.soft.kotlinfirebasetest.data.repository.UserRepositoryInterface

class IsLogged (private val repository: UserRepositoryInterface) : UseCase<Boolean,UseCase.None>() {
    override suspend fun run(params: None) = repository.isLogged()
}