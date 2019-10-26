package com.vortex.soft.kotlinfirebasetest.di.module

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import org.koin.dsl.module

val networkModule = module {
    single { FirebaseAuth.getInstance() }
    single { FirebaseDatabase.getInstance()}
}