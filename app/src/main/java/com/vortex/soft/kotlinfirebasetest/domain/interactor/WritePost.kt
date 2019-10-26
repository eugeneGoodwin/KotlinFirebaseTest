package com.vortex.soft.kotlinfirebasetest.domain.interactor

import com.vortex.soft.kotlinfirebasetest.data.repository.PostRepositoryInterface
import com.vortex.soft.kotlinfirebasetest.domain.model.DPost

class WritePost (private val repository: PostRepositoryInterface): UseCase<String, DPost>() {
    override suspend fun run(params: DPost) = repository.writePost(params)
}