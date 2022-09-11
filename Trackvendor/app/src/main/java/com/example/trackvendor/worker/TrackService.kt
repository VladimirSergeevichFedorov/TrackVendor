package com.example.trackvendor.worker

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationChannelGroup
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkRequest
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.example.trackvendor.BuildConfig
import com.example.trackvendor.R
import com.example.trackvendor.WifiStateApplication
import com.example.trackvendor.storage.StorageImpl
import com.example.trackvendor.ui.MainActivity
import com.example.trackvendor.utils.TrackNetworkState
import javax.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class TrackService : Service() {

    @Inject
    lateinit var networkRequest: NetworkRequest

    @Inject
    lateinit var storageImpl: StorageImpl

    @Inject
    lateinit var trackNetworkState: TrackNetworkState.Factory

    private fun getConnectivityManager() =
        getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        (application as WifiStateApplication).component.inject(this)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (intent?.action != null && intent.action.equals(
                ACTION_STOP, ignoreCase = true
            )
        ) {
            stopForeground(true)
            stopSelf()
        }
        generateForegroundNotification()
        networkState()
        return START_STICKY
    }

    private var notification: Notification? = null
    var mNotificationManager: NotificationManager? = null

    private fun networkState() {
        val networkState = trackNetworkState.create { nameWifi, dateWiFikState, stateWiFi ->
            scope.launch {
                storageImpl.saveConnectData(
                    connectingChangeDataKey = dateWiFikState,
                    connectingChangeData = dateWiFikState,
                    stateWiFi = stateWiFi,
                    nameWifi = nameWifi
                )
            }
        }

        getConnectivityManager().registerNetworkCallback(
            networkRequest,
            networkState.networkCallBack()
        )
        networkRequest
    }

    private fun generateForegroundNotification() {
        val intentMainLanding = Intent(this, MainActivity::class.java)
        val pendingIntent =
            PendingIntent.getActivity(this, 0, intentMainLanding, 0)
        if (mNotificationManager == null) {
            mNotificationManager =
                this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        }
            assert(mNotificationManager != null)
            mNotificationManager?.createNotificationChannelGroup(
                NotificationChannelGroup(CHAT_DEFAULT_NAME_ID, "")
            )
            val notificationChannel =
                NotificationChannel(
                    CHANNEL_DEFAULT_IMPORTANCE, getString(R.string.service_not),
                    NotificationManager.IMPORTANCE_MIN
                )
            notificationChannel.enableLights(false)
            notificationChannel.lockscreenVisibility = Notification.VISIBILITY_SECRET
            mNotificationManager?.createNotificationChannel(notificationChannel)
        val builder = NotificationCompat.Builder(this, CHANNEL_DEFAULT_IMPORTANCE)

        builder.setContentTitle(getString(R.string.notification_title))
            .setTicker(getString(R.string.notification_title))
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setOnlyAlertOnce(true)
            .setContentIntent(pendingIntent)
            .setOngoing(true)
        notification = builder.build()
        startForeground(ONGOING_NOTIFICATION_ID, notification)
    }

    companion object {
        const val ACTION_STOP = "${BuildConfig.APPLICATION_ID}.stop"
        const val ONGOING_NOTIFICATION_ID = 123
        const val CHANNEL_DEFAULT_IMPORTANCE = "service_channel"
        const val CHAT_DEFAULT_NAME_ID = "chats_group"

    }
}

