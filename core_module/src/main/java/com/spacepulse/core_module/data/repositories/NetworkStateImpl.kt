package com.spacepulse.core_module.data.repositories

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.spacepulse.core_module.domain.repositories.NetworkStateRepo
import javax.inject.Inject

class NetworkStateImpl @Inject constructor(

    private val connectivityManager: ConnectivityManager,
) : NetworkStateRepo {

    //implementation of internet connectivity
    override fun isInternetConnected(): Boolean {

        val network = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
        return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)

    }

}