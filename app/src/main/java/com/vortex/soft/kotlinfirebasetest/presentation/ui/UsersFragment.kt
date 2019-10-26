package com.vortex.soft.kotlinfirebasetest.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.vortex.soft.kotlinfirebasetest.R
import com.vortex.soft.kotlinfirebasetest.data.repository.Error
import com.vortex.soft.kotlinfirebasetest.domain.model.DUser
import com.vortex.soft.kotlinfirebasetest.presentation.adapters.UsersAdapter
import com.vortex.soft.kotlinfirebasetest.presentation.toolbar.UsersToolbarPresenter
import com.vortex.soft.kotlinfirebasetest.presentation.toolbar.UsersToolbarView
import com.vortex.soft.kotlinfirebasetest.presentation.viewmodel.MainViewModel
import com.vortex.soft.kotlinfirebasetest.presentation.viewmodel.UsersViewModel
import com.vortex.soft.kotlinfirebasetest.utils.extension.observe
import com.vortex.soft.kotlinfirebasetest.utils.extension.toast
import com.vortex.soft.kotlinfirebasetest.utils.extension.viewModel
import kotlinx.android.synthetic.main.fragment_users.*
import org.koin.androidx.scope.currentScope
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class UsersFragment: BaseFragment() {

    private val usersAdapter: UsersAdapter by currentScope.inject()
    private val usersViewModel: UsersViewModel by sharedViewModel()
    private val mainViewModel by sharedViewModel<MainViewModel>()
    override fun layoutId() = R.layout.fragment_users

    private lateinit var toolbarView: UsersToolbarView
    private var presenter: UsersToolbarPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel(usersViewModel) {
            observe(users, ::renderUsersList)
            observe(failure, ::renderError)
        }
        initializeView()
        loadUsersList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter?.unsubscribe()
    }

    private fun initializeView() {
        initializeToolbarView()
        with(usersList) {
            layoutManager = LinearLayoutManager(context)
            adapter = usersAdapter
        }
    }

    private fun initializeToolbarView() {
        toolbarView = UsersToolbarView.inflate(context!!)
        toolbarView.setTitle(getString(R.string.users))
        presenter = UsersToolbarPresenter(toolbarView, this)
        presenter?.subscribe()
        with(activity as MainActivity){ switchToolbar(toolbarView) }
    }

    private fun renderUsersList(dusers: List<DUser>?) {
        usersAdapter.users = dusers.orEmpty()
        hideProgress()
        context?.toast(getString(R.string.users_load_success))
    }

    private fun renderError(fail: Error?) {
        showError(fail)
        hideProgress()
    }

    private fun loadUsersList() {
        showProgress()
        usersViewModel.loadUsers()
    }

    fun reload() = loadUsersList()

    fun logOut() = mainViewModel.logOut()
}