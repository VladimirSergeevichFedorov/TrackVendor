package com.example.trackvendor.worker

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkRequest
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.trackvendor.storage.StorageImpl
import com.example.trackvendor.utils.TrackNetworkState
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class TrackWiFiWorker @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted private val params: WorkerParameters,
    private val trackNetworkState: TrackNetworkState.Factory,
    private val storageImpl: StorageImpl,
    private val networkRequest: NetworkRequest
) : CoroutineWorker(context, params) {

    private fun getConnectivityManager() =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    override suspend fun doWork(): Result {
        return try {
            val networkState = trackNetworkState.create { nameWifi, dateWiFikState, stateWiFi ->
                scope.launch {
                    storageImpl.saveConnectData(
                        connectingChangeDataKey = dateWiFikState,
                        connectingChangeData = dateWiFikState,
                        stateWiFi = stateWiFi,
                        nameWifi = nameWifi
                    )
                }
            }

            getConnectivityManager().registerNetworkCallback(
                networkRequest,
                networkState.networkCallBack()
            )
            Result.success()
        } catch (throwable: Throwable) {
            Result.failure()
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(appContext: Context, params: WorkerParameters): TrackWiFiWorker
    }
}
