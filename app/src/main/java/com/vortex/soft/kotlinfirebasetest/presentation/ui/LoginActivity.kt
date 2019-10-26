package com.vortex.soft.kotlinfirebasetest.presentation.ui

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import com.vortex.soft.kotlinfirebasetest.R
import com.vortex.soft.kotlinfirebasetest.data.repository.Error
import com.vortex.soft.kotlinfirebasetest.presentation.toolbar.LoginToolbarPresenter
import com.vortex.soft.kotlinfirebasetest.presentation.toolbar.LoginToolbarView
import com.vortex.soft.kotlinfirebasetest.presentation.viewmodel.LoginViewModel
import com.vortex.soft.kotlinfirebasetest.utils.extension.observe
import com.vortex.soft.kotlinfirebasetest.utils.extension.viewModel
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.toolbar_menu.*
import org.jetbrains.anko.startActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : BaseActivity() {

    override fun getLayout() = R.layout.activity_login

    private val loginViewModel by viewModel<LoginViewModel>()

    private lateinit var toolbarView: LoginToolbarView
    private lateinit var presenter: LoginToolbarPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        viewModel(loginViewModel) {
            observe(successLogin, ::goToMainActivity)
            observe(failure, ::renderError)
        }
        login_button.setOnClickListener {
            showProgress()
            loginViewModel.logIn(login_email.text.toString(), login_password.text.toString())
        }
        initializeToolbarView()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unsubscribe()
    }

    private fun goToMainActivity(success: Boolean?) {
        startActivity<MainActivity>()
    }

    private fun renderError(fail: Error?) {
        showError(fail)
        hideProgress()
    }

    fun switchToolbar(toolbarView: View) {
        with(menu_toolbar_container as ViewGroup) {
            removeAllViews()
            addView(toolbarView)
        }
    }

    private fun initializeToolbarView() {
        toolbarView = LoginToolbarView.inflate(baseContext)
        toolbarView.setTitle(getString(R.string.login_title))
        presenter = LoginToolbarPresenter(toolbarView, this)
        presenter.subscribe()
        switchToolbar(toolbarView)
    }
}