package com.example.trackvendor.usecases

import com.example.trackvendor.storage.entities.ConnectionState
import kotlinx.coroutines.flow.Flow

interface StorageInterface {
      suspend fun saveConnectData(
        connectingChangeDataKey: String,
        connectingChangeData: String,
        stateWiFi: Boolean,
        nameWifi: String
    )

    suspend fun getConnectData(): Flow<List<ConnectionState>>

    suspend fun clearAllData()
}