package com.example.trackvendor.worker

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import javax.inject.Inject

class WorkerFactory @Inject constructor(
    private val workerFactory: TrackWiFiWorker.Factory,
) : WorkerFactory() {

    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters,
    ): ListenableWorker? {
        return when (workerClassName) {
            TrackWiFiWorker::class.java.name ->
                workerFactory.create(appContext, workerParameters)
            else -> null
        }
    }
}