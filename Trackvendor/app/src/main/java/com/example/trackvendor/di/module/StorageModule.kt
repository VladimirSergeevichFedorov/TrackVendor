package com.example.trackvendor.di.module

import android.content.Context
import androidx.room.Room
import com.example.trackvendor.storage.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class StorageModule {

    @Singleton
    @Provides
    fun providerDatabase(context: Context): AppDatabase {

        return Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "app_db")
            .fallbackToDestructiveMigration()
            .build()
    }
}