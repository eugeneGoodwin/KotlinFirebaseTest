package com.vortex.soft.kotlinfirebasetest.presentation.toolbar

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import androidx.appcompat.widget.AppCompatImageButton
import com.vortex.soft.kotlinfirebasetest.R
import kotlinx.android.synthetic.main.posts_toolbar.view.*

class PostsToolbarView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : RelativeLayout(context, attrs, defStyleAttr), PostsToolbarContract.View {

    private lateinit var presenter: PostsToolbarContract.Presenter

    override fun setPresenter(presenter: PostsToolbarContract.Presenter) {
        this.presenter = presenter
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        action_add_post.setOnClickListener {
            presenter.clickAddButton()
        }
        action_posts_refresh.setOnClickListener {
            presenter.clickReload()
        }
        action_posts_log_out.setOnClickListener {
            presenter.clickOut()
        }
    }

    override fun setTitle(title: String) {
        toolbar_details_title.text = title
    }

    override fun showAddButton() {
        if(action_add_post is AppCompatImageButton)
            action_add_post.setVisibility(View.VISIBLE)
    }

    override fun hideAddButton() {
        if(action_posts_log_out is AppCompatImageButton)
            action_posts_log_out.setVisibility(View.INVISIBLE)
    }

    companion object {
        fun inflate(ctx: Context): PostsToolbarView {
            return LayoutInflater.from(ctx).inflate(R.layout.posts_toolbar, null) as PostsToolbarView
        }
    }
}