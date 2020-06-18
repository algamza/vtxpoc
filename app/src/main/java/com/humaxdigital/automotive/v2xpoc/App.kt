package com.humaxdigital.automotive.v2xpoc

import android.app.Application
import com.humaxdigital.automotive.v2xpoc.presentation.di.mRepositoryModules
import com.humaxdigital.automotive.v2xpoc.presentation.di.mUseCaseModules
import com.humaxdigital.automotive.v2xpoc.presentation.di.mViewModels
import org.koin.android.ext.android.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        loadKoin()
    }

    private fun loadKoin() {
        startKoin(this,
            listOf(mRepositoryModules, mUseCaseModules, mViewModels)
        )
    }
}