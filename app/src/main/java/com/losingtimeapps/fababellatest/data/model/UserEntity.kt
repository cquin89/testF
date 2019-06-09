package com.losingtimeapps.fababellatest.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.losingtimeapps.fababellatest.data.utils.TABLE_USERS

@Entity(
    tableName = TABLE_USERS
)
data class UserEntity(

    @PrimaryKey val userName: String
)