package com.losingtimeapps.fababellatest.app

import android.app.Application
import android.content.Context

class App : Application() {

    private var component: ApplicationComponent? = null

    companion object {
        lateinit var INSTANCE: Context
    }


    override fun onCreate() {
        super.onCreate()
        INSTANCE = this

        getComponent().inject(this)
    }

    fun getComponent(): ApplicationComponent {
        if (component == null) {
            component = DaggerApplicationComponent.builder().application(this).build()
        }
        return component as ApplicationComponent
    }

}
