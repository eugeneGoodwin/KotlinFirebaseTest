package com.vortex.soft.kotlinfirebasetest.data.repository

import com.vortex.soft.kotlinfirebasetest.domain.model.DPost
import com.vortex.soft.kotlinfirebasetest.utils.functional.Either

interface PostRepositoryInterface {
    suspend fun posts(): Either<Error, List<DPost>>
    suspend fun writePost(post: DPost): Either<Error, String>
}