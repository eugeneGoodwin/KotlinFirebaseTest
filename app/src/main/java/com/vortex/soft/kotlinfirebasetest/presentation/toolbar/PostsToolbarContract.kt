package com.vortex.soft.kotlinfirebasetest.presentation.toolbar

interface PostsToolbarContract {
    interface View :
        BaseView<Presenter> {
        fun setTitle(title: String)
        fun showAddButton()
        fun hideAddButton()
    }
    interface Presenter : BasePresenter {
        fun clickAddButton()
        fun clickReload()
        fun clickOut()
    }
}