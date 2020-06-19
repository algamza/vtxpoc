package com.humaxdigital.automotive.v2xpoc.presentation.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.humaxdigital.automotive.v2xpoc.domain.usecases.GetCarUseCase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class TestService : Service() {

    val getCarUseCase : GetCarUseCase by inject()

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()

        GlobalScope.launch {
            getCarUseCase.getWarning().collect {value ->
                TODO()
            }
            getCarUseCase.getInform().collect {value ->
                TODO()
            }
            getCarUseCase.getSPaT().collect {value ->
                TODO()
            }
            getCarUseCase.getVehicle().collect {value ->
                TODO()
            }
        }
    }
}