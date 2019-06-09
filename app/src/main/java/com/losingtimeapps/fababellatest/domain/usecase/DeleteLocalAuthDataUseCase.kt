package com.losingtimeapps.fababellatest.domain.usecase

import com.losingtimeapps.fababellatest.domain.boundary.BaseScheduler
import com.losingtimeapps.fababellatest.domain.boundary.UserRepository
import com.losingtimeapps.fababellatest.domain.entity.ResponseModel
import com.losingtimeapps.fababellatest.domain.entity.User
import io.reactivex.Observable

class DeleteLocalAuthDataUseCase(
    val userRepository: UserRepository,
    val baseScheduler: BaseScheduler
) : UseCase(baseScheduler) {

    fun invoke(): Observable<ResponseModel<User>> {

        return userRepository.deleteLocalAuthData()
            .subscribeOn(baseScheduler.io())
            .observeOn(baseScheduler.ui())
            .doOnComplete {

            }.doOnError {  }
    }
}