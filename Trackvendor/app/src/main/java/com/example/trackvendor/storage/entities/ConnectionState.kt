package com.example.trackvendor.storage.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ConnectionState(
    @PrimaryKey val connectingChangeDataKey: String,
    @ColumnInfo(name = "connect_wifi") val connectingChangeData: String?,
    @ColumnInfo(name = "state_wifi") val stateWiFi: Boolean?,
    @ColumnInfo(name = "name_wifi") val nameWifi:String?
)
