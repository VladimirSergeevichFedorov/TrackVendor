package com.example.trackvendor.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.trackvendor.storage.entities.ConnectionState


@Database(entities = [ConnectionState::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun statusConnectionWiFiDaoDao(): StatusConnectionWiFiDao

//    companion object {
//        private const val DATABASE_NAME = "app_db"
//
//        @Volatile
//        private var INSTANCE: AppDatabase? = null
//
//        fun getInstance(context: Context): AppDatabase =
//            INSTANCE ?: synchronized(this) {
//                INSTANCE ?: buildDatabase(context)
//                    .also { INSTANCE = it }
//            }
//
//        private fun buildDatabase(context: Context): AppDatabase =
//            Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, DATABASE_NAME)
//                .fallbackToDestructiveMigration()
//                .build()
//    }
}