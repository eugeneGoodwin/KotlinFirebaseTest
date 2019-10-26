package com.vortex.soft.kotlinfirebasetest.presentation.ui

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.vortex.soft.kotlinfirebasetest.R
import com.vortex.soft.kotlinfirebasetest.data.repository.Error
import com.vortex.soft.kotlinfirebasetest.presentation.viewmodel.MainViewModel
import com.vortex.soft.kotlinfirebasetest.utils.extension.observe
import com.vortex.soft.kotlinfirebasetest.utils.extension.toast
import com.vortex.soft.kotlinfirebasetest.utils.extension.viewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar_layout.*
import kotlinx.android.synthetic.main.toolbar_menu.*
import org.jetbrains.anko.startActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {

    private val mainViewModel: MainViewModel by viewModel()
    override fun getLayout() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val drawerToggle: ActionBarDrawerToggle = object : ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            override fun onDrawerOpened(drawerView: View) {
                super.onDrawerOpened(drawerView)
                invalidateOptionsMenu()
            }

            override fun onDrawerClosed(drawerView: View) {
                super.onDrawerClosed(drawerView)
                invalidateOptionsMenu()
            }
        }

        drawerToggle.isDrawerIndicatorEnabled = true
        drawer_layout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()

        navigation_view.setNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.action_user -> {
                    toast(getString(R.string.users))
                    replaceFragment(UsersFragment())
                }
                R.id.action_post -> {
                    toast(getString(R.string.posts))
                    replaceFragment(PostsFragment())
                }
            }
            drawer_layout.closeDrawer(GravityCompat.START)
            true
        }

        viewModel(mainViewModel) {
            observe(isUserLogged, ::afterCheckSession)
            observe(isLogOut, ::signOut)
            observe(failure, ::renderError)
        }
        showProgress()
        mainViewModel.checkSession()
    }

    private fun signOut(isLogOut: Boolean?) {
        isLogOut?.let {
            if(it) goToLoginActivity()
        }
    }

    private fun afterCheckSession(isLogged: Boolean?) {
        hideProgress()
        isLogged?.let {
            if(it) replaceFragment(UsersFragment())
            else goToLoginActivity()
        }
    }

    private fun renderError(fail: Error?) {
        showError(fail)
        hideProgress()
    }

    private fun goToLoginActivity() {
        startActivity<LoginActivity>()
    }

    fun switchToolbar(toolbarView: View) {
        with(menu_toolbar_container as ViewGroup) {
            removeAllViews()
            addView(toolbarView)
        }
    }
}