package com.example.trackvendor.utils

import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner

const val fineLocationPermissionCode = 101

fun <T : ViewModel> getViewModel(owner: ViewModelStoreOwner, factory: ViewModelProvider.Factory, aClass: Class<T>): T {
    return ViewModelProvider(owner, factory)[aClass]
}

fun Activity.checkPermissions() : Boolean {
    null
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if ((ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {
                requestPermissions(arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), fineLocationPermissionCode)
                return false
            }
            if ((ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
                && (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_BACKGROUND_LOCATION) != PackageManager.PERMISSION_GRANTED)) {
                requestPermissions(arrayOf(android.Manifest.permission.ACCESS_BACKGROUND_LOCATION), fineLocationPermissionCode)
                return false
            }
            if ((ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
                && (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_BACKGROUND_LOCATION) == PackageManager.PERMISSION_GRANTED)) {

                return true
            }
        } else {
            if ((ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
                && (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_BACKGROUND_LOCATION) == PackageManager.PERMISSION_GRANTED)) {

                return true
            }
            else {
                requestPermissions(arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_BACKGROUND_LOCATION), fineLocationPermissionCode)
                return false
            }
        }
    } else {
        if ((ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {
            requestPermissions(arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), fineLocationPermissionCode)
            return false
        } else {

            return true
        }
    }
    return false
//    if ((ContextCompat.checkSelfPermission(mainActivity, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {
//        requestPermissions(arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), fineLocationPermissionCode)
//        return (isFineLocationConfirmed && externalConfirmed)
//    } else isFineLocationConfirmed = true
//    if ((ContextCompat.checkSelfPermission(mainActivity, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
//        && (ContextCompat.checkSelfPermission(mainActivity, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
//        requestPermissions(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE), externalPermissionCode)
//        return (isFineLocationConfirmed && externalConfirmed)
//    } else externalConfirmed = true
//    return (isFineLocationConfirmed && externalConfirmed)
}