package com.losingtimeapps.fababellatest.domain.usecase

import com.losingtimeapps.fababellatest.domain.boundary.BaseScheduler

open class UseCase(private val baseScheduler: BaseScheduler) {

    fun uiInvoke() = baseScheduler.ui()

    fun computationInvoke() = baseScheduler.computation()

    fun ioInvoke() = baseScheduler.io()
}