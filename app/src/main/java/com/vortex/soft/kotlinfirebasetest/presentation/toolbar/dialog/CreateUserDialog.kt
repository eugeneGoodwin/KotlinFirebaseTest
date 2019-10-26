package com.vortex.soft.kotlinfirebasetest.presentation.toolbar.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.vortex.soft.kotlinfirebasetest.R
import com.vortex.soft.kotlinfirebasetest.domain.model.DUser
import com.vortex.soft.kotlinfirebasetest.presentation.viewmodel.UsersViewModel
import kotlinx.android.synthetic.main.dialog_user_add_layout.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class CreateUserDialog : DialogFragment() {

    private val usersViewModel by sharedViewModel<UsersViewModel>()
    private var user: DUser? = null
    private var password: String = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.dialog_user_add_layout, container)
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
            if(validate()) {
                fillUser()
                user?.let {
                    usersViewModel.user = user
                    usersViewModel.register(it.email, password)
                    dismiss()
                }
            }
        }
        label_cancel.setOnClickListener { dismiss() }
    }

    private fun validate(): Boolean {
        var valid = true
        if(user_name.text.toString().isEmpty()) {
            user_name.error = "Required."
            valid = false
        }
        else  user_name.error = null
        if(user_email.text.toString().isEmpty()) {
            user_email.error = "Required."
            valid = false
        }
        else user_email.error = null
        if(user_phone.text.toString().isEmpty()) {
            user_phone.error = "Required."
            valid = false
        } else user_phone.error = null
        return valid
    }

    private fun fillUser() {
        password = user_password.text.toString()
        user = DUser(user_name.text.toString(), user_email.text.toString(), user_phone.text.toString(), user_website.text.toString())
    }

    companion object {
        fun newInstance(): CreateUserDialog {
            return CreateUserDialog()
        }
    }
}