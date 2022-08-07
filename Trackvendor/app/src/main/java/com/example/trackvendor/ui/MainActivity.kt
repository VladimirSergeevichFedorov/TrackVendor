package com.example.trackvendor.ui

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.trackvendor.R
import com.example.trackvendor.WifiStateApplication
import com.example.trackvendor.ui.screen.MainScreen
import com.example.trackvendor.ui.screen.appBar.AppBarState
import com.example.trackvendor.ui.theme.TrackvendorTheme
import com.example.trackvendor.utils.ViewModelFactory
import com.example.trackvendor.utils.getViewModel
import com.example.trackvendor.worker.TrackWiFiWorker
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var mainActivityViewModel: MainActivityViewModel

    private val workManager by lazy {
        WorkManager.getInstance(applicationContext)
    }

    private val constraints = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.CONNECTED)
        .setRequiresStorageNotLow(true)
        .setRequiresBatteryNotLow(true)
        .build()

    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("BatteryLife")
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as WifiStateApplication).component.inject(this)
        mainActivityViewModel =
            getViewModel(this, viewModelFactory, MainActivityViewModel::class.java)
        createOneTimeWorkRequest()
        setContent {
            var appBarState by remember {
                mutableStateOf(AppBarState())
            }
            TrackvendorTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(
                                    text = stringResource(R.string.app_name),
                                    modifier = Modifier
                                        .padding(start = dimensionResource(R.dimen.twenty_padding)),
                                    color = MaterialTheme.colors.primary,
                                    letterSpacing = 1.3.sp
                                )
                            },
                            actions = { appBarState.actions?.invoke(this) },
                            backgroundColor = MaterialTheme.colors.onSecondary
                        )
                    },

                ) {
                    MainScreen(
                        viewModel = mainActivityViewModel,
                        onComposing = { appBarState = it }
                    )
                }
            }
        }
        val intent = Intent(
            Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS,
            Uri.parse("package:$packageName")
        )
        startActivity(intent)
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
        ) {
            val permissions =
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            ActivityCompat.requestPermissions(this, permissions, 0)
        }
    }

    override fun onStop() {
        super.onStop()
        createDelayedWorkRequest()
    }

    private fun createOneTimeWorkRequest() {
        val imageWorker = OneTimeWorkRequestBuilder<TrackWiFiWorker>()
            .setConstraints(constraints)
            .addTag("imageWork")
            .build()
        workManager.enqueueUniqueWork(
            "oneTimeImageDownload",
            ExistingWorkPolicy.KEEP,
            imageWorker
        )
    }

    private fun createDelayedWorkRequest() {
        val imageWorker = OneTimeWorkRequestBuilder<TrackWiFiWorker>()
            .setConstraints(constraints)
            .setInitialDelay(5, TimeUnit.SECONDS)
            .addTag("imageWork")
            .build()
        workManager.enqueueUniqueWork(
            "delayedImageDownload",
            ExistingWorkPolicy.KEEP,
            imageWorker
        )
    }

    private fun createPeriodicWorkRequest() {
        val imageWorker =
            PeriodicWorkRequestBuilder<TrackWiFiWorker>(15, TimeUnit.MINUTES, 10, TimeUnit.MINUTES)
                .setConstraints(constraints)
                .addTag("imageWorker")
                .build()
        workManager.enqueueUniquePeriodicWork(
            "periodicImageDownload",
            ExistingPeriodicWorkPolicy.REPLACE,
            imageWorker
        )
    }
}
