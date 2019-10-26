package com.vortex.soft.kotlinfirebasetest.domain.interactor

import com.vortex.soft.kotlinfirebasetest.data.repository.PostRepositoryInterface
import com.vortex.soft.kotlinfirebasetest.domain.model.DPost

class GetPosts (private val repository: PostRepositoryInterface) : UseCase<List<DPost>,UseCase.None>() {
    override suspend fun run(params: None) = repository.posts()
}