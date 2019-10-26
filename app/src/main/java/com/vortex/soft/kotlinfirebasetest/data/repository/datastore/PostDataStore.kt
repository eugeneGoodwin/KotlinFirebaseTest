package com.vortex.soft.kotlinfirebasetest.data.repository.datastore

import com.vortex.soft.kotlinfirebasetest.data.model.Post
import com.vortex.soft.kotlinfirebasetest.data.repository.Error
import com.vortex.soft.kotlinfirebasetest.domain.model.DPost
import com.vortex.soft.kotlinfirebasetest.utils.functional.Either

interface PostDataStore {
    suspend fun posts(): Either<Error, List<DPost>>
    suspend fun writePost(post: Post): Either<Error, String>
}