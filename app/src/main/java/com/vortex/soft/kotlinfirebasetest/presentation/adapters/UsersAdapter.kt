package com.vortex.soft.kotlinfirebasetest.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vortex.soft.kotlinfirebasetest.R
import com.vortex.soft.kotlinfirebasetest.domain.model.DUser
import kotlinx.android.synthetic.main.users_list_item.view.*
import kotlin.properties.Delegates

class UsersAdapter : RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    internal var users: List<DUser> by Delegates.observable(emptyList()) {
        _,_,_ -> notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater.from(parent.context).inflate(
        R.layout.users_list_item, parent, false))

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(users[position])
    }

    override fun getItemCount() = users.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(user: DUser) {
            with(itemView) {
                user_website.text = user.website
                user_name.text = user.name
                user_email.text = user.email
            }
        }
    }
}