package com.vortex.soft.kotlinfirebasetest.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.vortex.soft.kotlinfirebasetest.R
import com.vortex.soft.kotlinfirebasetest.data.repository.Error
import com.vortex.soft.kotlinfirebasetest.utils.extension.toast
import kotlinx.android.synthetic.main.activity_main.*

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
    }

    protected abstract fun getLayout(): Int

    protected fun replaceFragment(fragment: Fragment, bundle: Bundle? = null) {
        fragment.arguments = bundle
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.fadein, R.anim.fadeout)
            .replace(R.id.container, fragment)
            .commitAllowingStateLoss()
        invalidateOptionsMenu()
    }

    protected fun replaceFragmentWithTag(fragment: Fragment, tag: String, bundle: Bundle? = null) {
        fragment.arguments = bundle
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.fadein, R.anim.fadeout)
            .replace(R.id.container, fragment, tag)
            .commitAllowingStateLoss()
        invalidateOptionsMenu()
    }

    internal fun showProgress() = progressStatus(View.VISIBLE)

    internal fun hideProgress() = progressStatus(View.GONE)


    private fun progressStatus(viewStatus: Int) {
        progressbar?.visibility = viewStatus
    }

    protected fun toastError(headerMessage: String, fail: Error?) {
        fail?.let { toast(headerMessage + ": " + it.getMessage()) } ?: run { toast(headerMessage) }
    }

    fun showDialog(dialog: DialogFragment, tag: String) {
        if (supportFragmentManager.findFragmentByTag(tag) == null) {
            dialog.show(supportFragmentManager, tag)
        }
    }

    protected fun showError(fail: Error?) {
        when(fail) {
            is Error.NetworkConnectionError -> toastError("Network connection error", fail)
            is Error.FirebaseError -> toastError("Firebase error", fail)
            is Error.UndefineError -> toastError("Undefine error", fail)
            is Error.ServerError -> toastError("Server error", fail)
            is Error -> toastError("Error", fail)
        }
    }
}