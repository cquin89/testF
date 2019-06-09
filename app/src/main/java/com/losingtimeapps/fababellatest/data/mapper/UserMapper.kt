package com.losingtimeapps.fababellatest.data.mapper

import com.losingtimeapps.fababellatest.data.model.UserEntity
import com.losingtimeapps.fababellatest.data.model.UserRemoteEntity
import com.losingtimeapps.fababellatest.domain.entity.User

class UserMapper {

    fun apply(userEntity: UserEntity): User {
        return User(
            userName = userEntity.userName
        )
    }

    fun apply(user: User): UserEntity {
        return UserEntity(
            userName = user.userName
        )
    }

    fun apply2(user: User): UserRemoteEntity {
        return UserRemoteEntity(
            userName = user.userName,
            password = user.password
        )
    }
}