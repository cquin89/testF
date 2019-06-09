package com.losingtimeapps.fababellatest.domain.boundary

import com.losingtimeapps.fababellatest.domain.entity.Indicator
import com.losingtimeapps.fababellatest.domain.entity.ResponseModel
import com.losingtimeapps.fababellatest.domain.entity.User
import io.reactivex.Observable
import io.reactivex.Single

interface UserRepository {

    fun login(user: User): Observable<ResponseModel<User>>

    fun saveLocalAuthData(user: User): Observable<ResponseModel<User>>

    fun deleteLocalAuthData(): Observable<ResponseModel<User>>

    fun getLocalAuthData(): Observable<ResponseModel<User>>

    fun registerUser(user: User): Observable<ResponseModel<User>>

}