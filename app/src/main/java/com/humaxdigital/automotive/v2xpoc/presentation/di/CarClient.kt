package com.humaxdigital.automotive.v2xpoc.presentation.di

import android.content.ComponentName
import android.content.Context
import android.content.ServiceConnection
import android.extension.car.CarEx
import android.os.IBinder
import android.util.Log

fun createCar(context: Context) : CarEx {
    var car = CarEx.createCar(context, object: ServiceConnection {
        override fun onServiceDisconnected(p0: ComponentName?) {
            Log.d("TEST", "onServiceDisconnected")
        }
        override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
            Log.d("TEST", "onServiceConnected")
        }
    })
    car.connect()
    return car
}