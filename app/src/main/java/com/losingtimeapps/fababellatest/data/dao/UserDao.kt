package com.losingtimeapps.fababellatest.data.dao

import androidx.room.*
import com.losingtimeapps.fababellatest.data.model.UserEntity
import com.losingtimeapps.fababellatest.data.model.UserRemoteEntity
import io.reactivex.Completable
import io.reactivex.Observable


@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    fun getUser(): Observable<List<UserEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveUser(user: UserEntity): Completable

    @Query("DELETE FROM users")
    fun deleteUser(): Completable


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveUser(user: UserRemoteEntity): Completable

    @Query("SELECT * FROM usersremote WHERE userName = :userName")
    fun getRemoteUser(userName: String): Observable<List<UserRemoteEntity>>
}