package com.vortex.soft.kotlinfirebasetest.data.mapper

import com.vortex.soft.kotlinfirebasetest.data.model.Post
import com.vortex.soft.kotlinfirebasetest.domain.model.DPost


class PostMapper : Mapper<Post, DPost> {
    override fun mapFromEntity(type: Post) = DPost(type.userKey, type.title, type.body)
    override fun mapToEntity(type: DPost): Post = Post(type.userKey, type.title, type.body)
}