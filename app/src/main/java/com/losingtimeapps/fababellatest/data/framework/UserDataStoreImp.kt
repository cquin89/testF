package com.losingtimeapps.fababellatest.data.framework

import com.losingtimeapps.fababellatest.data.UserDataStore
import com.losingtimeapps.fababellatest.data.local.UserDatabase
import com.losingtimeapps.fababellatest.data.mapper.UserMapper
import com.losingtimeapps.fababellatest.data.model.UserEntity
import com.losingtimeapps.fababellatest.data.model.UserRemoteEntity
import com.losingtimeapps.fababellatest.data.utils.VALID_PASSWORD
import com.losingtimeapps.fababellatest.data.utils.VALID_USERNAME
import com.losingtimeapps.fababellatest.domain.entity.ResponseModel
import com.losingtimeapps.fababellatest.domain.entity.User
import com.losingtimeapps.fababellatest.domain.utils.Error
import io.reactivex.Completable
import io.reactivex.Observable

class UserDataStoreImp(val userDatabase: UserDatabase) : UserDataStore {

    override fun saveRemoteUser(user: UserRemoteEntity): Completable {
        return userDatabase.usersRemote.saveRemoteUser(user)
    }

    override fun loginRemoteServer(user: User): Observable<List<UserRemoteEntity>> {

        return userDatabase.usersRemote.getRemoteUser(user.userName)


    }

    override fun saveLocalAuthData(user: UserEntity): Completable {
        return userDatabase.users.saveUser(user)
    }

    override fun getLocalAuthData(): Observable<List<UserEntity>> {
        return userDatabase.users.getUser()
    }

    override fun deleteLocalAuthData(): Completable {
        return userDatabase.users.deleteUser()
    }


}