package com.vortex.soft.kotlinfirebasetest.data.repository.datastore.firebase

import com.google.firebase.database.FirebaseDatabase
import com.vortex.soft.kotlinfirebasetest.data.mapper.PostMapper
import com.vortex.soft.kotlinfirebasetest.data.model.Post
import com.vortex.soft.kotlinfirebasetest.data.repository.Error
import com.vortex.soft.kotlinfirebasetest.data.repository.NetworkHandler
import com.vortex.soft.kotlinfirebasetest.data.repository.datastore.PostDataStore
import com.vortex.soft.kotlinfirebasetest.domain.model.DPost
import com.vortex.soft.kotlinfirebasetest.utils.extension.pushValue
import com.vortex.soft.kotlinfirebasetest.utils.extension.readList
import com.vortex.soft.kotlinfirebasetest.utils.functional.Either

class PostDataStoreFirebase (private val networkHandler: NetworkHandler,
                             private val firebaseDatabase: FirebaseDatabase,
                             private val mapper: PostMapper): PostDataStore {
    override suspend fun posts(): Either<Error, List<DPost>> {
        return when (networkHandler.isConnected) {
            true -> {
                try {
                    Either.Right(firebaseDatabase.reference.child("posts").readList<Post>().map { mapper.mapFromEntity(it) })
                } catch (exception: Exception) {
                    Either.Left(Error.FirebaseError(exception.message ?: "Undefine firebase error"))
                }
            }
            false, null -> Either.Left(Error.NetworkConnectionError("No connection"))
        }
    }

    override suspend fun writePost(post: Post): Either<Error, String> {
        try {
            val ref = firebaseDatabase.reference
                .child("posts")
            val key = ref.pushValue(post).key ?: return Either.Left(Error.FirebaseError("Key is null"))
            return Either.Right(key)
        } catch (exception: Exception) {
            return Either.Left(Error.FirebaseError(exception.message ?: "Undefine firebase error"))
        }
    }

}