package com.vortex.soft.kotlinfirebasetest.presentation.toolbar.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.vortex.soft.kotlinfirebasetest.R
import com.vortex.soft.kotlinfirebasetest.presentation.adapters.ShortUsersAdapter
import com.vortex.soft.kotlinfirebasetest.presentation.viewmodel.PostsViewModel
import com.vortex.soft.kotlinfirebasetest.utils.extension.observe
import kotlinx.android.synthetic.main.dialog_list_layout.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class SelectUserDialog : DialogFragment() {

    private val postsViewModel by sharedViewModel<PostsViewModel>()
    private lateinit var itemsAdapter: ShortUsersAdapter
    private lateinit var listener: OnUserSelectedListener

    fun setListener(listener: OnUserSelectedListener) {
        this.listener = listener
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.dialog_list_layout, container)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window?.requestFeature(Window.FEATURE_NO_TITLE)
        return dialog
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initializeView()
        postsViewModel.loadUsersWithKeys()
        with(postsViewModel) {
            observe(users){
                itemsAdapter.users = it?.toList() ?: listOf()
            }
        }
    }

    private fun initializeView() {
        with(item_listview) {
            layoutManager = LinearLayoutManager(context)
            itemsAdapter = ShortUsersAdapter(listener)
            adapter = itemsAdapter
        }
    }

    companion object {
        fun newInstance(): SelectUserDialog {
            return SelectUserDialog()
        }
    }
}