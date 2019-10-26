package com.vortex.soft.kotlinfirebasetest.data.repository

import com.google.firebase.auth.FirebaseUser
import com.vortex.soft.kotlinfirebasetest.data.model.User
import com.vortex.soft.kotlinfirebasetest.data.repository.datastore.UserDataStoreFactory
import com.vortex.soft.kotlinfirebasetest.domain.model.DUser
import com.vortex.soft.kotlinfirebasetest.utils.functional.Either


class UserRepository (private val factory: UserDataStoreFactory) : UserRepositoryInterface {

    override suspend fun register(email: String, password: String) : Either<Error, FirebaseUser> = factory.retrieveRemoteDataStore().register(email, password)
    override suspend fun users(): Either<Error, List<DUser>> = factory.retrieveRemoteDataStore().users()
    override fun isLogged(): Either<Error, Boolean> = factory.retrieveRemoteDataStore().isLogged()
    override suspend fun logIn(email: String, password: String): Either<Error, FirebaseUser> = factory.retrieveRemoteDataStore().logIn(email, password)
    override fun logOut() = factory.retrieveRemoteDataStore().logOut()
    override suspend fun writeUser(firebaseUser: FirebaseUser, user: User): Either<Error, String> = factory.retrieveRemoteDataStore().writeUser(firebaseUser, user)
    override suspend fun usersWithKeys(): Either<Error, Map<String, DUser>> = factory.retrieveRemoteDataStore().usersWithKeys()
}