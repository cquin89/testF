package com.losingtimeapps.fababellatest

import android.content.Context
import com.losingtimeapps.fababellatest.data.UserDataStore
import com.losingtimeapps.fababellatest.data.framework.UserDataStoreImp
import com.losingtimeapps.fababellatest.data.local.UserDatabase
import com.losingtimeapps.fababellatest.data.local.UserRepositoryImp
import com.losingtimeapps.fababellatest.data.mapper.IndicatorMapper
import com.losingtimeapps.fababellatest.data.mapper.UserMapper
import com.losingtimeapps.fababellatest.data.remote.IndicatorRepositoryImp
import com.losingtimeapps.fababellatest.data.remote.IndicatorService
import com.losingtimeapps.fababellatest.domain.boundary.BaseScheduler
import com.losingtimeapps.fababellatest.domain.boundary.IndicatorRepository
import com.losingtimeapps.fababellatest.domain.boundary.UserRepository
import com.losingtimeapps.fababellatest.domain.usecase.*
import com.losingtimeapps.fababellatest.presentation.schedule.ScheduleImp
import com.losingtimeapps.fababellatest.presentation.ui.MainViewModel

class InjectorHelper() {

    companion object {

        lateinit var indicatorService: IndicatorService
        lateinit var userDataStore: UserDataStore


        ////////// mapper module
        fun injectUserMapper(): UserMapper = UserMapper()

        fun injectIndicatorMapper() = IndicatorMapper()



        ///////////// local cache module
        fun injectUserDataStore(userDatabase: UserDatabase): UserDataStore = UserDataStoreImp(userDatabase)

        fun injectUserRepository(userDataStore: UserDataStore, userMapper: UserMapper): UserRepository =
            UserRepositoryImp(userDataStore, userMapper)

        fun injectIndicatorRepository(
            indicatorService: IndicatorService,
            indicatorMapper: IndicatorMapper
        ): IndicatorRepository =
            IndicatorRepositoryImp(indicatorService, indicatorMapper)

        fun initInjectionIndicatorRepository(indicatorService: IndicatorService): IndicatorRepository {
            return injectIndicatorRepository(
                indicatorService, injectIndicatorMapper()
            )
        }

        fun injectBaseScheduler(): BaseScheduler = TestScheduleImp()

        fun initInjectionUserRepository(userDataStore: UserDataStore): UserRepository {
            return injectUserRepository(
                userDataStore,
                injectUserMapper()
            )
        }

        fun injectLoginUseCase(): LoginUseCase {
            return LoginUseCase(
                initInjectionUserRepository(
                    userDataStore
                ), injectBaseScheduler()
            )
        }

        fun injectGetIndicatorUseCase(): GetIndicatorsUseCase {
            return GetIndicatorsUseCase(
                initInjectionIndicatorRepository(
                    indicatorService
                ), injectBaseScheduler()
            )
        }

        fun injectGetLocalAuthData(): GetLocalAuthDataUseCase {
            return GetLocalAuthDataUseCase(
                initInjectionUserRepository(
                    userDataStore
                ), injectBaseScheduler()
            )
        }

        fun injectDeleteLocalAuth(): DeleteLocalAuthDataUseCase {
            return DeleteLocalAuthDataUseCase(
                initInjectionUserRepository(
                    userDataStore
                ), injectBaseScheduler()
            )
        }

        fun injectRegisterUseCase(): RegisterUserUseCase {
            return RegisterUserUseCase(
                initInjectionUserRepository(
                    userDataStore
                ), injectBaseScheduler()
            )
        }

        fun injectMainViewModel(): MainViewModel {
            return MainViewModel(
                injectLoginUseCase(),
                injectGetIndicatorUseCase(),
                injectGetLocalAuthData(),
                injectDeleteLocalAuth(),
                injectRegisterUseCase()
            )
        }

    }


}