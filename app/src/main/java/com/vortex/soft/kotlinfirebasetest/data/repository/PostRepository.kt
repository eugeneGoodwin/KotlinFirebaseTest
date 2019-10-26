package com.vortex.soft.kotlinfirebasetest.data.repository

import com.vortex.soft.kotlinfirebasetest.data.mapper.PostMapper
import com.vortex.soft.kotlinfirebasetest.data.repository.datastore.PostDataStoreFactory
import com.vortex.soft.kotlinfirebasetest.domain.model.DPost
import com.vortex.soft.kotlinfirebasetest.utils.functional.Either

class PostRepository (private val factory: PostDataStoreFactory, private val mapper: PostMapper): PostRepositoryInterface {
    override suspend fun posts(): Either<Error, List<DPost>> = factory.retrieveRemoteDataStore().posts()
    override suspend fun writePost(post: DPost): Either<Error, String> = factory.retrieveRemoteDataStore().writePost(mapper.mapToEntity(post))
}