package com.vortex.soft.kotlinfirebasetest.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import com.vortex.soft.kotlinfirebasetest.domain.interactor.GetPosts
import com.vortex.soft.kotlinfirebasetest.domain.interactor.GetUsersWithKeys
import com.vortex.soft.kotlinfirebasetest.domain.interactor.UseCase
import com.vortex.soft.kotlinfirebasetest.domain.interactor.WritePost
import com.vortex.soft.kotlinfirebasetest.domain.model.DPost
import com.vortex.soft.kotlinfirebasetest.domain.model.DUser

class PostsViewModel (private val getPosts: GetPosts, private val writePost: WritePost, private val getUsersWithKeys: GetUsersWithKeys) : BaseViewModel() {

    var posts : MutableLiveData<List<DPost>> = MutableLiveData()
    var users : MutableLiveData<Map<String, DUser>> = MutableLiveData()

    fun loadPosts() = getPosts(scope, UseCase.None()) { it.fold(::handleFailure, ::handlePostsList)}

    private fun handlePostsList(dposts : List<DPost>) {
        this.posts.value = dposts
    }

    fun loadUsersWithKeys() = getUsersWithKeys(scope, UseCase.None()) { it.fold(::handleFailure, ::handleUsersMap) }

    private fun handleUsersMap(dusers : Map<String, DUser>) {
        users.value = dusers
    }

    fun writePost(post: DPost) = writePost(scope, post) { it.fold(::handleFailure, ::handleWritePost) }

    private fun handleWritePost(key: String) {
        loadPosts()
    }
}