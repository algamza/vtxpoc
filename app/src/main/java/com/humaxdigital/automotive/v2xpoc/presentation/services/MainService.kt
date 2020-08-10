package com.humaxdigital.automotive.v2xpoc.presentation.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.humaxdigital.automotive.v2xpoc.domain.usecases.GetCarUseCase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainService : Service() {

    val getCarUseCase : GetCarUseCase by inject()

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()

        GlobalScope.launch {
            /*
            getCarUseCase.callbackInform().collect {value ->
                // TODO()
            }

             */
        }
    }
}