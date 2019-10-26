package com.vortex.soft.kotlinfirebasetest.di.module

import com.vortex.soft.kotlinfirebasetest.data.mapper.PostMapper
import com.vortex.soft.kotlinfirebasetest.data.mapper.UserMapper
import com.vortex.soft.kotlinfirebasetest.data.repository.*
import com.vortex.soft.kotlinfirebasetest.data.repository.datastore.PostDataStore
import com.vortex.soft.kotlinfirebasetest.data.repository.datastore.PostDataStoreFactory
import com.vortex.soft.kotlinfirebasetest.data.repository.datastore.UserDataStore
import com.vortex.soft.kotlinfirebasetest.data.repository.datastore.UserDataStoreFactory
import com.vortex.soft.kotlinfirebasetest.data.repository.datastore.firebase.PostDataStoreFirebase
import com.vortex.soft.kotlinfirebasetest.data.repository.datastore.firebase.UserDataStoreFirebase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val repositoryModule = module {
    factory { NetworkHandler(androidApplication()) }

    factory <UserDataStore> { UserDataStoreFirebase(get(), get(), get(), mapper = UserMapper()) }
    factory <PostDataStore> { PostDataStoreFirebase(get(), get(), mapper = PostMapper()) }

    factory { UserDataStoreFactory(get()) }
    factory { PostDataStoreFactory(get()) }

    factory <UserRepositoryInterface> { UserRepository(get()) }
    factory <PostRepositoryInterface> { PostRepository(get(), mapper = PostMapper()) }
}