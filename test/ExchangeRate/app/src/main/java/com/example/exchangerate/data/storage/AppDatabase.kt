package com.example.exchangerate.data.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.exchangerate.data.storage.entities.ValuteEntity

@Database(entities = [ValuteEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun rateDao(): RateDao

}