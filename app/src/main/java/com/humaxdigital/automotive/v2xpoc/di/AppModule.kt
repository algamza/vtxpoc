package com.humaxdigital.automotive.v2xpoc.di

import android.speech.tts.TextToSpeech
import com.humaxdigital.automotive.v2xpoc.data.car.CarApi
import com.humaxdigital.automotive.v2xpoc.data.repository.CarRepositoryImpl
import com.humaxdigital.automotive.v2xpoc.domain.repositories.CarRepository
import com.humaxdigital.automotive.v2xpoc.domain.usecases.GetCarUseCase
import com.humaxdigital.automotive.v2xpoc.presentation.services.MainService
import com.humaxdigital.automotive.v2xpoc.presentation.test.TestViewModel
import com.humaxdigital.automotive.v2xpoc.presentation.v2x.*
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.fragment.dsl.fragment
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mRepositoryModules = module {
    single{ CarApi(get()) }
    single { CarRepositoryImpl(get()) as CarRepository }
}

val mAndroidModule = module {
    single { createCar(androidContext()) }
    single { TextToSpeech(androidContext()) {} }
}

val mUseCaseModules = module {
    factory { GetCarUseCase(get()) }
}

val mViewModels = module {
    viewModel { TestViewModel(get()) }
    viewModel { MainViewModel(androidContext(), get(), get()) }
}

val mAppModules = module {
    single { MainService() }
    fragment { MainFragment() }
    fragment { SideFragment() }
    fragment { LogFragment() }
    fragment { AVWFragment() }
    fragment { BSWFragment() }
    fragment { EBWFragment() }
    fragment { EVWCFragment() }
    fragment { EVWLFragment() }
    fragment { EVWRFragment() }
    fragment { FCWFragment() }
    fragment { ICWLFragment() }
    fragment { ICWRFragment() }
    fragment { LTAFragemnt() }
    fragment { RWWAFragment() }
    fragment { RWWLRFragment() }
}