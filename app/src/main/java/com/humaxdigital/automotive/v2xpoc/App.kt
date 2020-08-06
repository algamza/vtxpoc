package com.humaxdigital.automotive.v2xpoc

import android.app.Application
import com.humaxdigital.automotive.v2xpoc.di.mAndroidModule
import com.humaxdigital.automotive.v2xpoc.di.mRepositoryModules
import com.humaxdigital.automotive.v2xpoc.di.mUseCaseModules
import com.humaxdigital.automotive.v2xpoc.di.mViewModels
import org.koin.android.ext.android.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        loadKoin()
/*
        if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.O ) {
            startForegroundService(Intent(this, TestService::class.java))
        } else {
            startService(Intent(this, TestService::class.java))
        }
*/
    }

    private fun loadKoin() {
        startKoin(this,
            listOf(mAndroidModule, mRepositoryModules, mUseCaseModules, mViewModels)
        )
    }
}