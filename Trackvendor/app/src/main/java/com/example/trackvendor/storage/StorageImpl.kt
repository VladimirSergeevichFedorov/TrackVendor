package com.example.trackvendor.storage

import com.example.trackvendor.storage.entities.ConnectionState
import com.example.trackvendor.usecases.StorageInterface
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class StorageImpl @Inject constructor(
    private val database: AppDatabase
) : StorageInterface {

    override suspend fun saveConnectData(
        connectingChangeDataKey: String,
        connectingChangeData: String,
        stateWiFi: Boolean,
        nameWifi: String
    ) {
        database.statusConnectionWiFiDaoDao().insertConnect(
            ConnectionState(
                connectingChangeDataKey = connectingChangeDataKey,
                connectingChangeData = connectingChangeData,
                stateWiFi = stateWiFi,
                nameWifi = nameWifi
            )
        )
    }


    override suspend fun getConnectData(): Flow<List<ConnectionState>> {
//        return connection.getUser(1)
        return database.statusConnectionWiFiDaoDao().getAll()
    }

    override suspend fun clearAllData() {
        database.clearAllTables()
    }

}