package com.losingtimeapps.fababellatest.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.losingtimeapps.fababellatest.data.model.UserEntity
import com.losingtimeapps.fababellatest.data.model.UserRemoteEntity
import io.reactivex.Completable
import io.reactivex.Observable


@Dao
interface UsersRemoteDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveRemoteUser(user: UserRemoteEntity): Completable

    @Query("SELECT * FROM usersremote WHERE userName = :userName")
    fun getRemoteUser(userName: String): Observable<List<UserRemoteEntity>>
}