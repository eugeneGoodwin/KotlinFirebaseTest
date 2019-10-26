package com.vortex.soft.kotlinfirebasetest.data.mapper

import com.vortex.soft.kotlinfirebasetest.data.model.User
import com.vortex.soft.kotlinfirebasetest.domain.model.DUser

class UserMapper : Mapper<User, DUser> {
    override fun mapFromEntity(type: User) = DUser(type.name, type.email, type.phone, type.website)
    override fun mapToEntity(type: DUser): User = User(type.name, type.email, type.phone, type.website, "", "", "", "")
}