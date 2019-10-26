package com.vortex.soft.kotlinfirebasetest.data.repository.datastore

open class PostDataStoreFactory (private val remotePostDataStore: PostDataStore) {
    open fun retrieveRemoteDataStore(): PostDataStore = remotePostDataStore
}