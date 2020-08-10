package com.humaxdigital.automotive.v2xpoc

import android.app.Application
import com.humaxdigital.automotive.v2xpoc.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.context.startKoin

class App : Application() {
    public var startcount = 0
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
        startKoin {
            androidContext(this@App)
            fragmentFactory()
            modules(listOf(mAndroidModule, mRepositoryModules, mUseCaseModules, mViewModels, mAppModules))
        }
    }
}