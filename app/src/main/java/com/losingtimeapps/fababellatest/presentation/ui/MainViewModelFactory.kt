package com.losingtimeapps.fababellatest.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.losingtimeapps.fababellatest.domain.usecase.*

class MainViewModelFactory(
    val loginUseCase: LoginUseCase,
    val getIndicatorUseCase: GetIndicatorsUseCase,
    val getLocalAuthData: GetLocalAuthDataUseCase,
    val deleteLocalAuth: DeleteLocalAuthDataUseCase,
    val registerUserUseCase: RegisterUserUseCase
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(
            loginUseCase,
            getIndicatorUseCase,
            getLocalAuthData,
            deleteLocalAuth,
            registerUserUseCase

        ) as T
    }
}