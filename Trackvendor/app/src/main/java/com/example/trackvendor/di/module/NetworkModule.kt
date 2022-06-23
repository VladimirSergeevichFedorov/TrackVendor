package com.example.trackvendor.di.module

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkRequest

import dagger.Module
import dagger.Provides
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@Module
class NetworkModule {

    @Provides
    fun provideNetworkRequest(): NetworkRequest {
        return NetworkRequest.Builder()
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .build()
    }

//    @Provides
//    fun providesClassName(classNameFactory : TrackNetworkState.Factory) : TrackNetworkState{
//        return classNameFactory.create("")
//    }

//    @Provides
//    fun provideNetworkCallBack(
//        context: Context,
//        name: (String)->Unit
//    ): ConnectivityManager.NetworkCallback {
//        return TrackNetworkState(name = name,context = context).networkCallBack( )
//    }
//    @Provides
//    fun provideNetworkCallBacks(
//        context: Context,
//        name: String,
//        storageInterface: StorageInterface
//    ): TrackNetworkState {
//        return TrackNetworkState(name = name,context = context)
//    }
}