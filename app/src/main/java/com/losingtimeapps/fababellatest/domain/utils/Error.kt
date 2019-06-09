package com.losingtimeapps.fababellatest.domain.utils

import com.losingtimeapps.fababellatest.R
import retrofit2.HttpException
import java.net.UnknownHostException

class ParseError {

    fun parse(throwable: Throwable): Error {

        return when (throwable) {
            is HttpException -> when (throwable.code()) {
                404 -> Error.NotFoundError
                500 -> Error.InternalServerError
                else -> Error.UnexpectedError
            }
            is UnknownHostException -> Error.NetworkConnectionError
            else -> Error.UnexpectedError
        }
    }
}

enum class Error(val value: Int) {

    // register error
    UserAlreadyRegisteredError(R.string.app_name),

    // login error
    EmptyUserNameError(R.string.empty_user_name_error),
    EmptyPasswordError(R.string.empty_password_error),
    InvalidDataError(R.string.invalid_data),

    // User error
    UserNotLoginError(R.string.app_name),

    // indicator error
    GetIndicatorsError(R.string.app_name),

    // http error
    NotFoundError(R.string.app_name),
    NetworkConnectionError(R.string.network_connection_error),
    UnexpectedError(R.string.unexpected_error),
    InternalServerError(R.string.app_name)

}