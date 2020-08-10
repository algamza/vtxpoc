package com.humaxdigital.automotive.v2xpoc.presentation.test

import android.app.Application
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.KeyEvent
import com.humaxdigital.automotive.v2xpoc.App
import com.humaxdigital.automotive.v2xpoc.presentation.v2x.MainActivity

class GlobalKeyReceiver : BroadcastReceiver() {

    override fun onReceive(p0: Context?, p1: Intent?) {
        var app = p0!!.applicationContext as App
        if ( !p1!!.action.equals("android.intent.action.GLOBAL_BUTTON") ) return
        val event = p1!!.getParcelableExtra<KeyEvent>(Intent.EXTRA_KEY_EVENT)
        when(event.keyCode) {
            KeyEvent.KEYCODE_ENDCALL -> {
                if ( app.startcount++ < 10 ) return
                p0!!.startActivity(Intent(p0!!, MainActivity::class.java)
                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
            }
            else -> app.startcount = 0
        }
    }
}