package com.vortex.soft.kotlinfirebasetest.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vortex.soft.kotlinfirebasetest.R
import com.vortex.soft.kotlinfirebasetest.domain.model.DPost
import kotlinx.android.synthetic.main.posts_list_item.view.*
import kotlin.properties.Delegates

    class PostsAdapter : RecyclerView.Adapter<PostsAdapter.ViewHolder>() {

    internal var posts: List<DPost> by Delegates.observable(emptyList()) {
        _,_,_ -> notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater.from(parent.context).inflate(
        R.layout.posts_list_item, parent, false))

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(posts[position])
    }

    override fun getItemCount() = posts.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(post: DPost) {
            with(itemView) {
                post_user_key.text = post.userKey
                post_title.text = post.title
                post_body.text = post.body
            }
        }
    }
}