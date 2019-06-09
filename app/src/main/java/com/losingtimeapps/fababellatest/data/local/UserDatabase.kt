package com.losingtimeapps.fababellatest.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.losingtimeapps.fababellatest.data.dao.UserDao
import com.losingtimeapps.fababellatest.data.dao.UsersRemoteDao
import com.losingtimeapps.fababellatest.data.model.UserEntity
import com.losingtimeapps.fababellatest.data.model.UserRemoteEntity
import com.losingtimeapps.fababellatest.data.utils.DATABASE_VERSION


@Database(
    entities = [
        UserEntity::class,
        UserRemoteEntity::class
    ],
    version = DATABASE_VERSION,
    exportSchema = false
)
abstract class UserDatabase : RoomDatabase() {
    abstract val users: UserDao
    abstract val usersRemote: UsersRemoteDao
}