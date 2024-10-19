package com.spacepulse.core_module.di

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities.NET_CAPABILITY_INTERNET
import android.net.NetworkCapabilities.TRANSPORT_CELLULAR
import android.net.NetworkCapabilities.TRANSPORT_WIFI
import android.net.NetworkRequest
import com.spacepulse.core_module.data.repositories.DefaultDispatcherImpl
import com.spacepulse.core_module.data.repositories.NetworkStateImpl
import com.spacepulse.core_module.domain.repositories.DefaultDispatcherRepository
import com.spacepulse.core_module.domain.repositories.NetworkStateRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreModule {

    //single ton instance of dispatchers
    @Singleton
    @Provides
    fun dispatcherProvider(): DefaultDispatcherRepository = DefaultDispatcherImpl()

    //single tone instance of network request
    @Singleton
    @Provides
    fun provideNetworkRequest(): NetworkRequest {
        return NetworkRequest.Builder()
            .addCapability(NET_CAPABILITY_INTERNET)
            .addTransportType(TRANSPORT_CELLULAR)
            .addTransportType(TRANSPORT_WIFI)
            .build()
    }

    //single tone instance of connectivity manager
    @Singleton
    @Provides
    fun provideConnectivityManager(@ApplicationContext mContext: Context): ConnectivityManager =
        mContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    //single tone instance of network state
    @Singleton
    @Provides
    fun provideNetworkState(
        connectivityManager: ConnectivityManager,
    ): NetworkStateRepo =
        NetworkStateImpl(connectivityManager = connectivityManager)
}