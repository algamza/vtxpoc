package com.humaxdigital.automotive.v2xpoc.presentation.test

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class HiddenBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        when (intent.action) {
            /* Intent.ACTION_BOOT_COMPLETED, */ Intent.ACTION_MEDIA_MOUNTED -> {
                PackageUpdateService.checkAndUpdatePackage(context)
            }
        }
    }
}
