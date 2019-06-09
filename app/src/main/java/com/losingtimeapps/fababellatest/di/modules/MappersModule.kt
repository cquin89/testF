package com.losingtimeapps.fababellatest.di.modules

import com.losingtimeapps.fababellatest.data.mapper.IndicatorMapper
import com.losingtimeapps.fababellatest.data.mapper.UserMapper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal class MappersModule {

    @Provides
    @Singleton
    fun provideUserMapper():UserMapper= UserMapper()

    @Provides
    @Singleton
    fun provideIndicatorMapper():IndicatorMapper = IndicatorMapper()
}