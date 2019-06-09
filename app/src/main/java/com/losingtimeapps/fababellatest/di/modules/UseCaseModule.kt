package com.losingtimeapps.fababellatest.di.modules

import com.losingtimeapps.fababellatest.domain.boundary.BaseScheduler
import com.losingtimeapps.fababellatest.domain.boundary.IndicatorRepository
import com.losingtimeapps.fababellatest.domain.boundary.UserRepository
import com.losingtimeapps.fababellatest.domain.usecase.*
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun provideLoginUseCase(
        userRepository: UserRepository
        , baseScheduler: BaseScheduler
    ): LoginUseCase {
        return LoginUseCase(userRepository, baseScheduler)
    }


    @Provides
    fun provideRegisterUseCase(
        userRepository: UserRepository
        , baseScheduler: BaseScheduler
    ): RegisterUserUseCase {
        return RegisterUserUseCase(userRepository, baseScheduler)
    }

    @Provides
    fun provideGetIndicatorUseCase(
        indicatorRepository: IndicatorRepository
        , baseScheduler: BaseScheduler
    ): GetIndicatorsUseCase {
        return GetIndicatorsUseCase(indicatorRepository, baseScheduler)
    }

    @Provides
    fun provideGetLocalAuthData(userRepository: UserRepository, baseScheduler: BaseScheduler): GetLocalAuthDataUseCase {
        return GetLocalAuthDataUseCase(userRepository, baseScheduler)
    }

    @Provides
    fun provideDeleteLocalAuth(
        userRepository: UserRepository,
        baseScheduler: BaseScheduler
    ): DeleteLocalAuthDataUseCase {
        return DeleteLocalAuthDataUseCase(userRepository, baseScheduler)
    }
}