package com.spacepulse.core_module.data.repositories

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import junit.framework.TestCase.assertTrue

import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever


class NetworkStateImplTest {

    private lateinit var networkStateImpl: NetworkStateImpl
    private lateinit var connectivityManager: ConnectivityManager
    private lateinit var networkCapabilities: NetworkCapabilities
    private lateinit var network: Network

    @Before
    fun setup() {

        //mock connectivity manager
        connectivityManager = mock(ConnectivityManager::class.java)
        //mock network capabilities manager
        networkCapabilities = mock(NetworkCapabilities::class.java)
        //mock the network class
        network = mock(Network::class.java)
        networkStateImpl = NetworkStateImpl(connectivityManager)
    }

    //test case for capability with network connected
    @Test
    fun networkConnectedAndHasCapabilityTestCase() {
        //valid network availability
        whenever(connectivityManager.activeNetwork).thenReturn(network)
        //capability of active network
        whenever(connectivityManager.getNetworkCapabilities(network)).thenReturn(networkCapabilities)
        whenever(networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)).thenReturn(
            true
        )
        assertTrue(networkStateImpl.isInternetConnected())
    }

    //failure case when no active internet connection
    @Test
    fun noActiveInternetTestCase() {
        whenever(connectivityManager.activeNetwork).thenReturn(null)
        assertFalse(networkStateImpl.isInternetConnected())
    }


    @Test
    fun noInternetCapabilitiesTestCase() {
        whenever(connectivityManager.activeNetwork).thenReturn(network)
        whenever(connectivityManager.getNetworkCapabilities(network)).thenReturn(networkCapabilities)
        whenever(networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)).thenReturn(
            false
        )
        assertFalse(networkStateImpl.isInternetConnected())
    }

    @Test
    fun networkCapabilityNullTestCase() {
        whenever(connectivityManager.activeNetwork).thenReturn(network)
        whenever(connectivityManager.getNetworkCapabilities(network)).thenReturn(null)
        assertFalse(networkStateImpl.isInternetConnected())
    }
}
