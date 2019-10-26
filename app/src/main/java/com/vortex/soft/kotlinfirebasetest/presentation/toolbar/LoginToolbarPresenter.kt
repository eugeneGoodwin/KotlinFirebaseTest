package com.vortex.soft.kotlinfirebasetest.presentation.toolbar

import com.vortex.soft.kotlinfirebasetest.presentation.ui.LoginActivity

open class LoginToolbarPresenter(val view: LoginToolbarContract.View, var activity: LoginActivity?) : LoginToolbarContract.Presenter {
    init {
        view.setPresenter(this)
    }

    override fun subscribe() {}

    override fun unsubscribe() {
        activity = null
    }

    override fun clickAddButton() {}
}