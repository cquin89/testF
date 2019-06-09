package com.losingtimeapps.fababellatest.domain.usecase

import com.losingtimeapps.fababellatest.domain.boundary.BaseScheduler
import com.losingtimeapps.fababellatest.domain.boundary.UserRepository
import com.losingtimeapps.fababellatest.domain.entity.ResponseModel
import com.losingtimeapps.fababellatest.domain.entity.User
import com.losingtimeapps.fababellatest.domain.utils.Error
import io.reactivex.Observable

class RegisterUserUseCase(
    val userRepository: UserRepository,
    val baseScheduler: BaseScheduler
) : UseCase(baseScheduler) {

    fun invoke(user: User): Observable<ResponseModel<User>> {

        return when {
            user.userName.isEmpty() -> Observable.just(ResponseModel(Error.EmptyUserNameError))
            user.password.isEmpty() -> Observable.just(ResponseModel(Error.EmptyPasswordError))
            else -> userRepository.registerUser(user)
                .subscribeOn(baseScheduler.io())
                .observeOn(baseScheduler.ui())


        }


    }
}