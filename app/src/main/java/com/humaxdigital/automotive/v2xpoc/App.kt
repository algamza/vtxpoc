package com.humaxdigital.automotive.v2xpoc

import android.app.Application
import android.content.Intent
import com.humaxdigital.automotive.v2xpoc.presentation.di.mAndroidModule
import com.humaxdigital.automotive.v2xpoc.presentation.di.mRepositoryModules
import com.humaxdigital.automotive.v2xpoc.presentation.di.mUseCaseModules
import com.humaxdigital.automotive.v2xpoc.presentation.di.mViewModels
import com.humaxdigital.automotive.v2xpoc.presentation.services.TestService
import org.koin.android.ext.android.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        loadKoin()

        startService(Intent(this, TestService::class.java))
    }

    private fun loadKoin() {
        startKoin(this,
            listOf(mAndroidModule, mRepositoryModules, mUseCaseModules, mViewModels)
        )
    }
}