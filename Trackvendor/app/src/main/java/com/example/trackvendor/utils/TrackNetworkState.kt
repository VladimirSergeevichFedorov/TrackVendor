package com.example.trackvendor.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.WIFI_SERVICE
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.wifi.WifiInfo
import android.net.wifi.WifiManager
import android.os.Build
import android.widget.Toast
import com.example.trackvendor.R
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import java.text.SimpleDateFormat
import java.util.*

class TrackNetworkState @AssistedInject constructor(
    @Assisted("paramsNetworkState") val paramsNetworkState: (nameWifi: String, dateWifiState: String, stateWiFi: Boolean) -> Unit,
    private val context: Context
) {

    fun networkCallBack(
    ): ConnectivityManager.NetworkCallback {

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            object : ConnectivityManager.NetworkCallback(FLAG_INCLUDE_LOCATION_INFO) {

                override fun onLost(network: Network) {    //when Wifi 【turns off】
                    super.onLost(network)
                    val ssid = context.getString(R.string.unknown_ssid)
                    val dateFormat =
                        SimpleDateFormat(context.getString(R.string.retrofit_gson_date_format))
                    val currentDate: String = dateFormat.format(Date())
                    paramsNetworkState.invoke(ssid, currentDate, false)
                    Toast.makeText(context, "Wifi turns off!", Toast.LENGTH_SHORT).show()
                }


                override fun onCapabilitiesChanged(
                    network: Network,
                    networkCapabilities: NetworkCapabilities
                ) {
                    super.onCapabilitiesChanged(network, networkCapabilities)

                    val dateFormat =
                        SimpleDateFormat(context.getString(R.string.retrofit_gson_date_format))
                    val currentDate: String = dateFormat.format(Date())

                    val wifiInfo = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        networkCapabilities.transportInfo as WifiInfo?

                    } else {
                        TODO("VERSION.SDK_INT < Q")
                    }
                    val ssid = wifiInfo?.ssid
                    paramsNetworkState.invoke(ssid.orEmpty(), currentDate, true)
                    Toast.makeText(context, "$ssid", Toast.LENGTH_SHORT).show()
                }

            }
        } else {
            object : ConnectivityManager.NetworkCallback() {
                @SuppressLint("SimpleDateFormat")
                override fun onAvailable(network: Network) {    //when Wifi is on
                    super.onAvailable(network)
                    val sdf =
                        SimpleDateFormat(context.getString(R.string.retrofit_gson_date_format))
                    val wifiManager =
                        context.applicationContext.getSystemService(WIFI_SERVICE) as WifiManager
                    val currentDate: String = sdf.format(Date())
                    val wifiInfo: WifiInfo = wifiManager.connectionInfo
                    val ssid = wifiInfo.ssid
                    paramsNetworkState.invoke(ssid, currentDate, true)
                    Toast.makeText(context, "$ssid ${currentDate}", Toast.LENGTH_SHORT).show()
                }

                @SuppressLint("SimpleDateFormat")
                override fun onLost(network: Network) {    //when Wifi 【turns off】
                    super.onLost(network)
                    val sdf =
                        SimpleDateFormat(context.getString(R.string.retrofit_gson_date_format))
                    val wifiManager =
                        context.applicationContext.getSystemService(WIFI_SERVICE) as WifiManager
                    val currentDate: String = sdf.format(Date())
                    val wifiInfo: WifiInfo = wifiManager.connectionInfo
                    val ssid = wifiInfo.ssid
                    paramsNetworkState.invoke(ssid, currentDate, false)
                    Toast.makeText(context, currentDate, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("paramsNetworkState") paramsNetworkState: (nameWifi: String, dateWifiState: String, stateWiFi: Boolean) -> Unit
        ): TrackNetworkState
    }
}
