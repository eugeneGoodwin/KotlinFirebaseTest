package com.vortex.soft.kotlinfirebasetest.presentation.toolbar

import com.vortex.soft.kotlinfirebasetest.presentation.toolbar.dialog.CreateUserDialog
import com.vortex.soft.kotlinfirebasetest.presentation.ui.MainActivity
import com.vortex.soft.kotlinfirebasetest.presentation.ui.UsersFragment

open class UsersToolbarPresenter(val view: UsersToolbarContract.View, var fragment: UsersFragment?) : UsersToolbarContract.Presenter {
    init {
        view.setPresenter(this)
    }

    override fun subscribe() {}

    override fun unsubscribe() {
        fragment = null
    }

    override fun clickAddButton() {
        fragment?.let{
            (it.requireActivity() as MainActivity).showDialog(CreateUserDialog.newInstance(), "CreateUserDialog")
        }
    }

    override fun clickOut() {
        fragment?.logOut()
    }

    override fun clickReload() {
        fragment?.reload()
    }
}