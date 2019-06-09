package com.losingtimeapps.fababellatest.data

import com.losingtimeapps.fababellatest.data.model.UserEntity
import com.losingtimeapps.fababellatest.data.model.UserRemoteEntity
import com.losingtimeapps.fababellatest.domain.entity.ResponseModel
import com.losingtimeapps.fababellatest.domain.entity.User
import io.reactivex.Completable
import io.reactivex.Observable


interface UserDataStore {

    fun loginRemoteServer(user: User): Observable<List<UserRemoteEntity>>
    fun saveRemoteUser(user: UserRemoteEntity): Completable

    fun saveLocalAuthData(user: UserEntity): Completable
    fun getLocalAuthData(): Observable<List<UserEntity>>
    fun deleteLocalAuthData(): Completable
}