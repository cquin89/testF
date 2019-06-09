package com.losingtimeapps.fababellatest.data.local

import com.losingtimeapps.fababellatest.data.UserDataStore
import com.losingtimeapps.fababellatest.data.mapper.UserMapper
import com.losingtimeapps.fababellatest.data.utils.VALID_PASSWORD
import com.losingtimeapps.fababellatest.data.utils.VALID_USERNAME
import com.losingtimeapps.fababellatest.domain.boundary.UserRepository
import com.losingtimeapps.fababellatest.domain.entity.ResponseModel
import com.losingtimeapps.fababellatest.domain.entity.User
import com.losingtimeapps.fababellatest.domain.utils.Error
import io.reactivex.Observable

class UserRepositoryImp(val userDataStore: UserDataStore, val userMapper: UserMapper) : UserRepository {


    override fun login(user: User): Observable<ResponseModel<User>> {
        return userDataStore.loginRemoteServer(user).map {

            if (it.isNotEmpty()) {
                if (user.userName == it[0].userName && user.password == it[0].password) {
                    ResponseModel(user)
                } else {
                    ResponseModel(Error.InvalidDataError)
                }
            } else {
                ResponseModel(Error.InvalidDataError)
            }
        }

    }

    override fun saveLocalAuthData(user: User): Observable<ResponseModel<User>> {
        return userDataStore.saveLocalAuthData(userMapper.apply(user)).toObservable()

    }

    override fun deleteLocalAuthData(): Observable<ResponseModel<User>> {
        return userDataStore.deleteLocalAuthData().toObservable()

    }

    override fun getLocalAuthData(): Observable<ResponseModel<User>> {
        return userDataStore.getLocalAuthData().map {
            if (it.isEmpty())
                ResponseModel(Error.UserNotLoginError)
            else
                ResponseModel(userMapper.apply(it[0]))
        }
    }

    override fun registerUser(user: User): Observable<ResponseModel<User>> {
        return userDataStore.saveRemoteUser(userMapper.apply2(user)).toObservable()

    }
}