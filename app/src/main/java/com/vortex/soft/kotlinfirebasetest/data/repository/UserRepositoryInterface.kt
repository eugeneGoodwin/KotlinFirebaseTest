package com.vortex.soft.kotlinfirebasetest.data.repository

import com.google.firebase.auth.FirebaseUser
import com.vortex.soft.kotlinfirebasetest.data.model.User
import com.vortex.soft.kotlinfirebasetest.domain.interactor.UseCase.None
import com.vortex.soft.kotlinfirebasetest.domain.model.DUser
import com.vortex.soft.kotlinfirebasetest.utils.functional.Either

interface UserRepositoryInterface {
    suspend fun register(email: String, password: String) : Either<Error, FirebaseUser>
    suspend fun logIn(email: String, password: String): Either<Error, FirebaseUser>
    fun isLogged(): Either<Error, Boolean>
    fun logOut(): Either<Error, None>
    suspend fun writeUser(firebaseUser: FirebaseUser, user: User): Either<Error, String>
    suspend fun users(): Either<Error, List<DUser>>
    suspend fun usersWithKeys(): Either<Error, Map<String, DUser>>
}