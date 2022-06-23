package com.example.trackvendor.di.module

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.trackvendor.storage.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineScope


@Module
class StorageModule {
//    @Provides
//    fun providerDatabases(context: Context): AppDatabase =
//        AppDatabase.getInstance(context)

    @Singleton
    @Provides
    fun providerDatabase(context: Context): AppDatabase {

        return Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "app_db")
            .fallbackToDestructiveMigration()
            .build()
    }
}