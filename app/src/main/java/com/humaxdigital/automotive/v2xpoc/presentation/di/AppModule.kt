package com.humaxdigital.automotive.v2xpoc.presentation.di


import com.humaxdigital.automotive.v2xpoc.data.car.CarApi
import com.humaxdigital.automotive.v2xpoc.data.repository.CarRepositoryImpl
import com.humaxdigital.automotive.v2xpoc.domain.repositories.CarRepository
import com.humaxdigital.automotive.v2xpoc.domain.usecases.GetCarUseCase
import com.humaxdigital.automotive.v2xpoc.presentation.v2x.TestMainViewModel
import com.humaxdigital.automotive.v2xpoc.presentation.v2x.TestViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module


val mRepositoryModules = module {
    single(name=CAR) { createCar(androidApplication()) }
    single(name=CAR_API) { CarApi(get(CAR)) }
    single { CarRepositoryImpl(api = get(CAR_API)) as CarRepository }
}

val mUseCaseModules = module {
    factory(name=GET_CAR_USECASE) { GetCarUseCase(repositories = get()) }
}

val mViewModels = module {
    viewModel { TestViewModel(getCarUseCase = get(GET_CAR_USECASE)) }
    viewModel { TestMainViewModel(getCarUseCase = get(GET_CAR_USECASE)) }
}

private const val CAR = "car"
private const val CAR_API = "carapi"
private const val GET_CAR_USECASE = "getCarUseCase"