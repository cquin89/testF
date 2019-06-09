package com.losingtimeapps.fababellatest.app

import android.content.Context
import com.losingtimeapps.fababellatest.di.qualifiers.ForApplication
import com.losingtimeapps.fababellatest.domain.boundary.BaseScheduler
import com.losingtimeapps.fababellatest.presentation.schedule.ScheduleImp
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {

    @ForApplication
    @Provides
    internal fun provideApplicationContext(app: App): Context {
        return app.applicationContext
    }

    @Provides
    fun provideScheduler(): BaseScheduler = ScheduleImp()

}
