package com.losingtimeapps.fababellatest.di.modules

import android.content.Context
import androidx.room.Room
import com.losingtimeapps.fababellatest.app.App
import com.losingtimeapps.fababellatest.data.UserDataStore
import com.losingtimeapps.fababellatest.data.framework.UserDataStoreImp
import com.losingtimeapps.fababellatest.data.local.UserDatabase
import com.losingtimeapps.fababellatest.data.local.UserRepositoryImp
import com.losingtimeapps.fababellatest.data.mapper.IndicatorMapper
import com.losingtimeapps.fababellatest.data.mapper.UserMapper
import com.losingtimeapps.fababellatest.data.remote.IndicatorRepositoryImp
import com.losingtimeapps.fababellatest.data.remote.IndicatorService
import com.losingtimeapps.fababellatest.data.utils.DATABASE_NAME
import com.losingtimeapps.fababellatest.domain.boundary.IndicatorRepository
import com.losingtimeapps.fababellatest.domain.boundary.UserRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideRooom(): UserDatabase =
        Room.databaseBuilder(
            App.INSTANCE,
            UserDatabase::class.java,
            DATABASE_NAME
        ).build()

    @Provides
    fun provideUserDataStore(userDatabase: UserDatabase): UserDataStore = UserDataStoreImp(userDatabase)

    @Provides
    fun provideUserRepository(userDataStore: UserDataStore, userMapper: UserMapper): UserRepository =
        UserRepositoryImp(userDataStore, userMapper)

    @Provides
    fun provideIndicatorRepository(
        indicatorService: IndicatorService,
        indicatorMapper: IndicatorMapper
    ): IndicatorRepository =
        IndicatorRepositoryImp(indicatorService, indicatorMapper)

}