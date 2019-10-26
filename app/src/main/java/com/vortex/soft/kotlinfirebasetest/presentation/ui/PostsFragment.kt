package com.vortex.soft.kotlinfirebasetest.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.vortex.soft.kotlinfirebasetest.R
import com.vortex.soft.kotlinfirebasetest.data.repository.Error
import com.vortex.soft.kotlinfirebasetest.domain.model.DPost
import com.vortex.soft.kotlinfirebasetest.presentation.adapters.PostsAdapter
import com.vortex.soft.kotlinfirebasetest.presentation.toolbar.PostsToolbarPresenter
import com.vortex.soft.kotlinfirebasetest.presentation.toolbar.PostsToolbarView
import com.vortex.soft.kotlinfirebasetest.presentation.viewmodel.MainViewModel
import com.vortex.soft.kotlinfirebasetest.presentation.viewmodel.PostsViewModel
import com.vortex.soft.kotlinfirebasetest.utils.extension.observe
import com.vortex.soft.kotlinfirebasetest.utils.extension.toast
import com.vortex.soft.kotlinfirebasetest.utils.extension.viewModel
import kotlinx.android.synthetic.main.fragment_posts.*
import org.koin.androidx.scope.currentScope
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class PostsFragment : BaseFragment() {

    private val postsAdapter: PostsAdapter by currentScope.inject()
    private val postsViewModel: PostsViewModel by sharedViewModel()
    private val mainViewModel by sharedViewModel<MainViewModel>()
    override fun layoutId() = R.layout.fragment_posts

    private lateinit var toolbarView: PostsToolbarView
    private var presenter: PostsToolbarPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel(postsViewModel) {
            observe(posts, ::renderPostsList)
            observe(failure, ::renderError)
        }
        initializeView()
        loadPostsList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter?.unsubscribe()
    }

    private fun initializeView() {
        initializeToolbarView()
        with(postsList) {
            layoutManager = LinearLayoutManager(context)
            adapter = postsAdapter
        }
    }

    private fun initializeToolbarView() {
        toolbarView = PostsToolbarView.inflate(context!!)
        toolbarView.setTitle("Posts")
        presenter = PostsToolbarPresenter(toolbarView, this)
        presenter?.subscribe()
        with(activity as MainActivity){ switchToolbar(toolbarView) }
    }

    private fun renderPostsList(dposts: List<DPost>?) {
        postsAdapter.posts = dposts.orEmpty()
        hideProgress()
        context?.toast("Posts success load")
    }

    private fun renderError(fail: Error?) {
        showError(fail)
        hideProgress()
    }

    private fun loadPostsList() {
        showProgress()
        postsViewModel.loadPosts()
    }

    fun reload() = loadPostsList()

    fun logOut() =  mainViewModel.logOut()
}