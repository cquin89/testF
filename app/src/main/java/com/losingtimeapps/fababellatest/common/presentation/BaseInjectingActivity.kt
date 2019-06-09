package com.losingtimeapps.fababellatest.common.presentation

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

abstract class BaseInjectingActivity<Component> : AppCompatActivity() {


    var component: Component? = null
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        component = createComponent()
        onInject(component!!)

        super.onCreate(savedInstanceState)
        oncreate()
    }


    protected abstract fun onInject(component: Component)

    protected abstract fun createComponent(): Component

    protected abstract fun oncreate()
}
