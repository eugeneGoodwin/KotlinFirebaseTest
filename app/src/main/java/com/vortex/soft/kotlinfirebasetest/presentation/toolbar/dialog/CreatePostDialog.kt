package com.vortex.soft.kotlinfirebasetest.presentation.toolbar.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.vortex.soft.kotlinfirebasetest.R
import com.vortex.soft.kotlinfirebasetest.domain.model.DPost
import com.vortex.soft.kotlinfirebasetest.domain.model.DUser
import com.vortex.soft.kotlinfirebasetest.presentation.ui.MainActivity
import com.vortex.soft.kotlinfirebasetest.presentation.viewmodel.PostsViewModel
import kotlinx.android.synthetic.main.dialog_post_add_layout.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class CreatePostDialog : DialogFragment() {

    private val postsViewModel by sharedViewModel<PostsViewModel>()
    private var post: DPost? = null
    private var selectedUser: Pair<String, DUser>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.dialog_post_add_layout, container)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        with(dialog) {
            window?.requestFeature(Window.FEATURE_NO_TITLE)
            setCanceledOnTouchOutside(false)
        }
        return dialog
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        label_save.setOnClickListener {
            if (validate()) {
                fillPost()
                post?.let {
                    postsViewModel.writePost(it)
                    dismiss()
                }
            }
        }
        label_cancel.setOnClickListener { dismiss() }
        post_userKey.setOnClickListener {
            val dialog = SelectUserDialog.newInstance()
            dialog.setListener( object: OnUserSelectedListener {
                override fun onItemSelected(selectedItem: Pair<String, DUser>) {
                    selectedUser = selectedItem
                    post_userKey.text = selectedUser?.first ?: ""
                    dialog.dismiss()
                }
            })
            (requireActivity() as MainActivity).showDialog(dialog, "SelectUserDialog")
        }
    }

    private fun validate(): Boolean {
        var valid = true
        if (post_title.text.toString().isEmpty()) {
            post_title.error = "Required."
            valid = false
        } else post_title.error = null
        if (post_userKey.text.toString().isEmpty()) {
            post_userKey.error = "Required."
            valid = false
        } else post_userKey.error = null
        return valid
    }

    private fun fillPost() {
        post = DPost(
            post_userKey.text.toString(),
            post_title.text.toString(),
            post_body.text.toString()
        )
    }

    companion object {
        fun newInstance(): CreatePostDialog {
            return CreatePostDialog()
        }
    }
}