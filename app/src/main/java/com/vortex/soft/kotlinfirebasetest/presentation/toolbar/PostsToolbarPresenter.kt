package com.vortex.soft.kotlinfirebasetest.presentation.toolbar

import com.vortex.soft.kotlinfirebasetest.presentation.toolbar.dialog.CreatePostDialog
import com.vortex.soft.kotlinfirebasetest.presentation.ui.MainActivity
import com.vortex.soft.kotlinfirebasetest.presentation.ui.PostsFragment

open class PostsToolbarPresenter(val view: PostsToolbarContract.View, var fragment: PostsFragment?) : PostsToolbarContract.Presenter {
    init {
        view.setPresenter(this)
    }

    override fun subscribe() {}

    override fun unsubscribe() {
        fragment = null
    }

    override fun clickAddButton() {
        fragment?.let{
             (it.requireActivity() as MainActivity).showDialog(CreatePostDialog.newInstance(), "CreatePostDialog")
        }
    }

    override fun clickReload() {
        fragment?.reload()
    }

    override fun clickOut() {
        fragment?.logOut()
    }
}