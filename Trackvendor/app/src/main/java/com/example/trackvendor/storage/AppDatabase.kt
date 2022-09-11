package com.example.trackvendor.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.trackvendor.storage.entities.ConnectionState


@Database(entities = [ConnectionState::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun statusConnectionWiFiDaoDao(): StatusConnectionWiFiDao

}