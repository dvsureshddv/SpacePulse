package com.spacepulse.network_module.di

import com.spacepulse.core_module.domain.repositories.DefaultDispatcherRepository
import com.spacepulse.core_module.domain.repositories.NetworkStateRepo
import com.spacepulse.network_module.data.api.ConnectionApi
import com.spacepulse.network_module.data.repositories.NewsRepositoryImpl
import com.spacepulse.network_module.domain.repositories.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    //provide singleton instance of okhttp client
    @Provides
    @Singleton
    fun provideOkhttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }

    //provide singleton instance of retrofit client
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {

        return Retrofit.Builder()
            .baseUrl("https://api.spaceflightnewsapi.net/v4/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    //provide singleton instance of connection api
    @Provides
    @Singleton
    fun provideConnectionApi(retrofit: Retrofit): ConnectionApi {
        return retrofit.create(ConnectionApi::class.java)
    }

    //provide singleton instance of news repo
    @Provides
    @Singleton
    fun provideNewsRepository(
        connectionApi: ConnectionApi,
        dispatcherRepo: DefaultDispatcherRepository,
        networkStateRepo: NetworkStateRepo
    ): NewsRepository = NewsRepositoryImpl(
        connectionApi = connectionApi,
        dispatcherRepo = dispatcherRepo,
        networkStateRepo = networkStateRepo
    )

}