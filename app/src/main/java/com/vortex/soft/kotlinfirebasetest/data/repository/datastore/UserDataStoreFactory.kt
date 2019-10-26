package com.vortex.soft.kotlinfirebasetest.data.repository.datastore

open class UserDataStoreFactory (private val remoteUserDataStore: UserDataStore) {
    open fun retrieveRemoteDataStore(): UserDataStore = remoteUserDataStore
}