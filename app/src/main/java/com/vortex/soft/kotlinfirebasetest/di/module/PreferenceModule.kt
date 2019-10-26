package com.vortex.soft.kotlinfirebasetest.di.module

import android.preference.PreferenceManager
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val preferenceModule = module {
    single { PreferenceManager.getDefaultSharedPreferences(androidContext()) }
}