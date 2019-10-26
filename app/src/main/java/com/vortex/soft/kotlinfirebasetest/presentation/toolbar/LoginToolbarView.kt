package com.vortex.soft.kotlinfirebasetest.presentation.toolbar

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import androidx.appcompat.widget.AppCompatImageButton
import com.vortex.soft.kotlinfirebasetest.R
import kotlinx.android.synthetic.main.login_toolbar.view.*

class LoginToolbarView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : RelativeLayout(context, attrs, defStyleAttr), LoginToolbarContract.View {

    private lateinit var presenter: LoginToolbarContract.Presenter

    override fun setPresenter(presenter: LoginToolbarContract.Presenter) {
        this.presenter = presenter
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        action_add_user.setOnClickListener {
            presenter.clickAddButton()
        }
        hideAddButton()
    }

    override fun setTitle(title: String) {
        toolbar_details_title.text = title
    }

    override fun showAddButton() {
        if(action_add_user is AppCompatImageButton)
            action_add_user.setVisibility(View.VISIBLE)
    }

    override fun hideAddButton() {
        if(action_add_user is AppCompatImageButton)
            action_add_user.setVisibility(View.INVISIBLE)
    }

    companion object {
        fun inflate(ctx: Context): LoginToolbarView {
            return LayoutInflater.from(ctx).inflate(R.layout.login_toolbar, null) as LoginToolbarView
        }
    }
}