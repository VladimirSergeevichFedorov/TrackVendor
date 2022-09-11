package com.example.trackvendor.ui

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import com.example.trackvendor.R
import com.example.trackvendor.WifiStateApplication
import com.example.trackvendor.ui.screen.MainScreen
import com.example.trackvendor.ui.screen.appBar.AppBarState
import com.example.trackvendor.ui.theme.TrackvendorTheme
import com.example.trackvendor.utils.ViewModelFactory
import com.example.trackvendor.utils.getViewModel
import com.example.trackvendor.worker.TrackService
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var mainActivityViewModel: MainActivityViewModel

    @SuppressLint("BatteryLife")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as WifiStateApplication).component.inject(this)
        mainActivityViewModel =
            getViewModel(this, viewModelFactory, MainActivityViewModel::class.java)
        startService(Intent(this, TrackService::class.java))

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

}
