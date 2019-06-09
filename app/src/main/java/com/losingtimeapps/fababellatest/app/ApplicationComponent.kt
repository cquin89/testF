package com.losingtimeapps.fababellatest.app


import com.losingtimeapps.fababellatest.di.modules.*
import com.losingtimeapps.fababellatest.di.modules.MappersModule
import com.losingtimeapps.fababellatest.di.modules.NetworkModule
import com.losingtimeapps.fababellatest.presentation.ui.MainActivityComponent
import dagger.BindsInstance
import dagger.Component

import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, NetworkModule::class, MappersModule::class,
    RepositoryModule::class,UseCaseModule::class])
interface ApplicationComponent {

    fun inject(app: App)

    fun createMainActivityComponent(module: ActivityModule): MainActivityComponent

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: App): Builder

        fun build(): ApplicationComponent
    }
}
