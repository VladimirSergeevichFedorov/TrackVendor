package com.example.trackvendor.storage

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.trackvendor.storage.entities.ConnectionState

import kotlinx.coroutines.flow.Flow

@Dao
interface StatusConnectionWiFiDao {

    @Query("SELECT * FROM connectionState")
    fun getAll(): Flow<List<ConnectionState>>

    @Query("SELECT * FROM connectionState WHERE connectingChangeDataKey = :id")
    fun getConnectionState(id: String): Flow <ConnectionState>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<ConnectionState>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertConnect(user: ConnectionState)

    @Delete
    suspend fun delete(user: ConnectionState)

}