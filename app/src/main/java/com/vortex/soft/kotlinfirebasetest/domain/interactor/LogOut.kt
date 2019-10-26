package com.vortex.soft.kotlinfirebasetest.domain.interactor

import com.vortex.soft.kotlinfirebasetest.data.repository.UserRepositoryInterface

class LogOut (private val repository: UserRepositoryInterface) : UseCase<UseCase.None,UseCase.None>() {
    override suspend fun run(params: None) = repository.logOut()
}