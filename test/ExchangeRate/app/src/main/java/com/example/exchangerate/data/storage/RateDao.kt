package com.example.exchangerate.data.storage

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.exchangerate.data.storage.entities.ValuteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RateDao {
    @Query("SELECT * FROM valuteEntity")
    fun getAll(): Flow<List<ValuteEntity>>

    @Query("SELECT * FROM valuteEntity WHERE valuteId = :id")
    fun getValute(id: String): Flow<ValuteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(valuteList: List<ValuteEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertValute(valute: ValuteEntity)

    @Delete
    suspend fun delete(valute: ValuteEntity)
}