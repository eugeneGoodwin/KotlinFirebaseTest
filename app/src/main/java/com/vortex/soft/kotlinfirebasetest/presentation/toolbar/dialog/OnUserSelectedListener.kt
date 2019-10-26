package com.vortex.soft.kotlinfirebasetest.presentation.toolbar.dialog

import com.vortex.soft.kotlinfirebasetest.domain.model.DUser

interface OnUserSelectedListener {
    fun onItemSelected(selectedItem: Pair<String, DUser>)
}