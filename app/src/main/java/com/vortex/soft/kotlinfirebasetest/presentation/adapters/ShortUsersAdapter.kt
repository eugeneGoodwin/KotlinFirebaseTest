package com.vortex.soft.kotlinfirebasetest.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vortex.soft.kotlinfirebasetest.R
import com.vortex.soft.kotlinfirebasetest.domain.model.DUser
import com.vortex.soft.kotlinfirebasetest.presentation.toolbar.dialog.OnUserSelectedListener
import kotlinx.android.synthetic.main.short_user_item.view.*
import kotlin.properties.Delegates

class ShortUsersAdapter(private val listener: OnUserSelectedListener) : RecyclerView.Adapter<ShortUsersAdapter.ViewHolder>() {

    internal var users: List<Pair<String, DUser>> by Delegates.observable(emptyList()) {
            _,_,_ -> notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.short_user_item, parent, false))

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        with(viewHolder) {
            bind(users[position])
            itemView.setOnClickListener {
                listener.onItemSelected(users[position])
            }
        }
    }

    override fun getItemCount() = users.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(user: Pair<String, DUser>) {
            itemView.user_name .text = user.second.name
            itemView.user_key.text = user.first
        }
    }
}