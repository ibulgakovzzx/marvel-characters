package com.cookiedev.move.data.network.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.example.marver_characters.data.network.NetworkConnectionException

class ConnectionManager(private val context: Context) {
    private val networkInfo: NetworkInfo?
        get() = (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo

    fun checkConnection() {
        if (!isConnected) {
            throw NetworkConnectionException()
        }
    }

    val isConnected: Boolean
        get() = networkInfo?.isConnected == true
}