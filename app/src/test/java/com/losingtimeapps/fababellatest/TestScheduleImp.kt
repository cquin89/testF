package com.losingtimeapps.fababellatest

import com.losingtimeapps.fababellatest.domain.boundary.BaseScheduler
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class TestScheduleImp : BaseScheduler {

    override fun computation(): Scheduler = Schedulers.trampoline()


    override fun io(): Scheduler = Schedulers.trampoline()


    override fun ui(): Scheduler =Schedulers.trampoline()

}