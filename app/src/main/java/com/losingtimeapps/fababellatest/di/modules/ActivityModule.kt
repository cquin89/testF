package com.losingtimeapps.fababellatest.di.modules

import android.content.Context
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProviders
import com.losingtimeapps.fababellatest.di.qualifiers.ForActivity
import com.losingtimeapps.fababellatest.domain.usecase.*
import com.losingtimeapps.fababellatest.presentation.ui.MainActivity
import com.losingtimeapps.fababellatest.presentation.ui.MainViewModel
import com.losingtimeapps.fababellatest.presentation.ui.MainViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: AppCompatActivity) {

    @ForActivity
    @Provides
    internal fun provideContext(): Context {
        return activity
    }

    @Provides
    internal fun provideFragmentManager(activity: AppCompatActivity): FragmentManager {
        return activity.supportFragmentManager
    }

    @Provides
    fun provideMainViewModelFactory(
        loginUseCase: LoginUseCase,
        getIndicatorsUseCase: GetIndicatorsUseCase,
        getLocalAuthDataUseCase: GetLocalAuthDataUseCase,
        deleteLocalAuthDataUseCase: DeleteLocalAuthDataUseCase,
        registerUserUseCase: RegisterUserUseCase
    ) = MainViewModelFactory(
        loginUseCase,
        getIndicatorsUseCase,
        getLocalAuthDataUseCase,
        deleteLocalAuthDataUseCase,
        registerUserUseCase

    )

    @Provides
    fun provideMainViewModel(@ForActivity activity: Context, mainViewModelFactory: MainViewModelFactory)
            : MainViewModel = ViewModelProviders.of(activity as MainActivity, mainViewModelFactory)
        .get(MainViewModel::class.java)

}

