package com.vortex.soft.kotlinfirebasetest.presentation.toolbar

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import androidx.appcompat.widget.AppCompatImageButton
import com.vortex.soft.kotlinfirebasetest.R
import kotlinx.android.synthetic.main.users_toolbar.view.*

class UsersToolbarView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : RelativeLayout(context, attrs, defStyleAttr), UsersToolbarContract.View {

    private lateinit var presenter: UsersToolbarContract.Presenter

    override fun setPresenter(presenter: UsersToolbarContract.Presenter) {
        this.presenter = presenter
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        action_users_add_user.setOnClickListener {
            presenter.clickAddButton()
        }
        action_users_refresh.setOnClickListener {
            presenter.clickReload()
        }
        action_users_log_out.setOnClickListener {
            presenter.clickOut()
        }
    }

    override fun setTitle(title: String) {
        toolbar_details_title.text = title
    }

    override fun showAddButton() {
        if(action_users_add_user is AppCompatImageButton)
            action_users_add_user.setVisibility(View.VISIBLE)
    }

    override fun hideAddButton() {
        if(action_users_add_user is AppCompatImageButton)
            action_users_add_user.setVisibility(View.INVISIBLE)
    }

    companion object {
        fun inflate(ctx: Context): UsersToolbarView {
            return LayoutInflater.from(ctx).inflate(R.layout.users_toolbar, null) as UsersToolbarView
        }
    }
}