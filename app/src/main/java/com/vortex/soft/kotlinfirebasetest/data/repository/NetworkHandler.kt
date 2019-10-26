package com.vortex.soft.kotlinfirebasetest.data.repository

import android.content.Context
import android.net.ConnectivityManager

class NetworkHandler (private val context:Context){
    val isConnected get() = (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo?.isConnectedOrConnecting
}