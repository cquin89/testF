package com.losingtimeapps.fababellatest.presentation.ui

import com.losingtimeapps.fababellatest.di.scopes.ActivityScope
import com.losingtimeapps.fababellatest.di.modules.ActivityModule

import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [ActivityModule::class])
interface MainActivityComponent {

    fun inject(homeActivity: MainActivity)
}
