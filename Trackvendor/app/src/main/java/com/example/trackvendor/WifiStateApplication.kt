package com.example.trackvendor

import android.app.Application
import androidx.work.Configuration
import androidx.work.WorkManager
import com.example.trackvendor.di.AppComponent
import com.example.trackvendor.di.DaggerAppComponent
import com.example.trackvendor.worker.WorkerFactory
import javax.inject.Inject

class WifiStateApplication : Application() {

    lateinit var component: AppComponent

    @Inject
    lateinit var sampleWorkerFactory: WorkerFactory

    override fun onCreate() {
        component = DaggerAppComponent.builder()
            .applicationContext(applicationContext)
            .build()
        component.inject(this)

        super.onCreate()

        val workManagerConfig = Configuration.Builder()
            .setWorkerFactory(sampleWorkerFactory)
            .build()
        WorkManager.initialize(this, workManagerConfig)
    }
}