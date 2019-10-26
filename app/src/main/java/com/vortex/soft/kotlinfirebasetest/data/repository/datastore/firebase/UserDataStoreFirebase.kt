package com.vortex.soft.kotlinfirebasetest.data.repository.datastore.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import com.vortex.soft.kotlinfirebasetest.data.mapper.UserMapper
import com.vortex.soft.kotlinfirebasetest.data.model.User
import com.vortex.soft.kotlinfirebasetest.data.repository.Error
import com.vortex.soft.kotlinfirebasetest.data.repository.NetworkHandler
import com.vortex.soft.kotlinfirebasetest.data.repository.datastore.UserDataStore
import com.vortex.soft.kotlinfirebasetest.domain.interactor.UseCase.None
import com.vortex.soft.kotlinfirebasetest.domain.model.DUser
import com.vortex.soft.kotlinfirebasetest.utils.extension.await
import com.vortex.soft.kotlinfirebasetest.utils.extension.readList
import com.vortex.soft.kotlinfirebasetest.utils.extension.readMap
import com.vortex.soft.kotlinfirebasetest.utils.extension.saveValue
import com.vortex.soft.kotlinfirebasetest.utils.functional.Either

class UserDataStoreFirebase (private val networkHandler: NetworkHandler,
                             private val firebaseAuth: FirebaseAuth,
                             private val firebaseDatabase: FirebaseDatabase,
                             private val mapper: UserMapper): UserDataStore {
    override suspend fun register(email: String, password: String): Either<Error, FirebaseUser> {
        return when (networkHandler.isConnected) {
            true -> {
                try {
                    val task = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
                    if(task.user != null) Either.Right(task.user!!) else Either.Left(Error.FirebaseError("FirebaseUser = null"))
                } catch (exception: Exception) {
                    Either.Left(Error.FirebaseError(exception.message ?: "Undefine firebase error"))
                }
            }
            false, null -> Either.Left(Error.NetworkConnectionError("No connection"))
        }
    }

    override suspend fun logIn(email: String, password: String): Either<Error, FirebaseUser> {
        return when (networkHandler.isConnected) {
            true -> {
                try {
                    val task = firebaseAuth.signInWithEmailAndPassword(email, password).await()
                    return if(task.user != null) Either.Right(task.user!!) else Either.Left(Error.FirebaseError("FirebaseUser = null"))
                } catch (exception: Exception) {
                    Either.Left(Error.FirebaseError(exception.message ?: "Undefine firebase error"))
                }
            }
            false, null -> Either.Left(Error.NetworkConnectionError("No connection"))
        }
    }

    override fun isLogged(): Either<Error, Boolean> = Either.Right(firebaseAuth.currentUser != null)

    override fun logOut(): Either<Error, None> {
        firebaseAuth.signOut()
        return Either.Right(None())
    }

    override suspend fun users(): Either<Error, List<DUser>> {
        return when (networkHandler.isConnected) {
            true -> {
                try {
                    Either.Right(firebaseDatabase.reference.child("users").readList<User>().map { mapper.mapFromEntity(it) })
                } catch (exception: Exception) {
                    Either.Left(Error.FirebaseError(exception.message ?: "Undefine firebase error"))
                }
            }
            false, null -> Either.Left(Error.NetworkConnectionError("No connection"))
        }
    }

    override suspend fun writeUser(firebaseUser: FirebaseUser, user: User): Either<Error, String> {
        try {
            firebaseDatabase.reference.child("users").child(firebaseUser.uid).saveValue(user)
            return Either.Right(firebaseUser.uid)
        } catch (exception: Exception) {
            return Either.Left(Error.FirebaseError(exception.message ?: "Undefine firebase error"))
        }
    }

    override suspend fun usersWithKeys(): Either<Error, Map<String, DUser>> {
        return when (networkHandler.isConnected) {
            true -> {
                try {
                    Either.Right(firebaseDatabase.reference.child("users").readMap<User>().map { it.key to mapper.mapFromEntity(it.value) }.toMap())
                } catch (exception: Exception) {
                    Either.Left(Error.FirebaseError(exception.message ?: "Undefine firebase error"))
                }
            }
            false, null -> Either.Left(Error.NetworkConnectionError("No connection"))
        }
    }
}