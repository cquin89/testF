package com.losingtimeapps.fababellatest.domain.usecase

import com.losingtimeapps.fababellatest.domain.boundary.BaseScheduler
import com.losingtimeapps.fababellatest.domain.boundary.UserRepository
import com.losingtimeapps.fababellatest.domain.entity.ResponseModel
import com.losingtimeapps.fababellatest.domain.entity.User
import com.losingtimeapps.fababellatest.domain.utils.Error
import io.reactivex.Observable


class GetLocalAuthDataUseCase(
    val userRepository: UserRepository,
    val baseScheduler: BaseScheduler
) : UseCase(baseScheduler) {

    fun invoke(): Observable<ResponseModel<User>> {

        return userRepository.getLocalAuthData()
            .subscribeOn(baseScheduler.io())
            .observeOn(baseScheduler.ui())
    }
}