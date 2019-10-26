package com.vortex.soft.kotlinfirebasetest.di.module

import com.vortex.soft.kotlinfirebasetest.data.mapper.UserMapper
import com.vortex.soft.kotlinfirebasetest.domain.interactor.*
import com.vortex.soft.kotlinfirebasetest.presentation.adapters.PostsAdapter
import com.vortex.soft.kotlinfirebasetest.presentation.adapters.UsersAdapter
import com.vortex.soft.kotlinfirebasetest.presentation.ui.PostsFragment
import com.vortex.soft.kotlinfirebasetest.presentation.ui.UsersFragment
import com.vortex.soft.kotlinfirebasetest.presentation.viewmodel.LoginViewModel
import com.vortex.soft.kotlinfirebasetest.presentation.viewmodel.MainViewModel
import com.vortex.soft.kotlinfirebasetest.presentation.viewmodel.PostsViewModel
import com.vortex.soft.kotlinfirebasetest.presentation.viewmodel.UsersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val viewModelModule = module {
    factory { GetUsers(get()) }
    factory { GetUsersWithKeys(get()) }
    factory { GetPosts(get()) }
    factory { LogIn(get()) }
    factory { LogOut(get()) }
    factory { Register(get()) }
    factory { IsLogged(get())}
    factory { WritePost(get()) }
    factory { WriteUser(get(), mapper = UserMapper()) }

    viewModel { UsersViewModel(get(), get(), get()) }
    viewModel { PostsViewModel(get(), get(), get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { MainViewModel(get(), get())}

    scope(named<UsersFragment>()) {
        scoped { UsersAdapter() }
    }

    scope(named<PostsFragment>()) {
        scoped { PostsAdapter() }
    }
}