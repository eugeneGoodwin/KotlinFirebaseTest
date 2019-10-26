package com.vortex.soft.kotlinfirebasetest.di.component

import com.vortex.soft.kotlinfirebasetest.di.module.networkModule
import com.vortex.soft.kotlinfirebasetest.di.module.preferenceModule
import com.vortex.soft.kotlinfirebasetest.di.module.repositoryModule
import com.vortex.soft.kotlinfirebasetest.di.module.viewModelModule

val appComponent = listOf(networkModule, viewModelModule, repositoryModule, preferenceModule)