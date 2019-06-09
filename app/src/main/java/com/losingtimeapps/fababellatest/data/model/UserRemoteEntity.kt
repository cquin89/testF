package com.losingtimeapps.fababellatest.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.losingtimeapps.fababellatest.data.utils.TABLE_USERS
import com.losingtimeapps.fababellatest.data.utils.TABLE_USERS_REMOTE

@Entity(
    tableName = TABLE_USERS_REMOTE
)
data class UserRemoteEntity(

    @PrimaryKey val userName: String,
    val password: String?
)