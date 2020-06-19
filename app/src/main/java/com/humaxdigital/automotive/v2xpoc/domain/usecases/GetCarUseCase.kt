package com.humaxdigital.automotive.v2xpoc.domain.usecases

import com.humaxdigital.automotive.v2xpoc.domain.entities.*
import com.humaxdigital.automotive.v2xpoc.domain.repositories.CarRepository
import kotlinx.coroutines.flow.Flow

class GetCarUseCase(private val repositories: CarRepository) {
    fun getWarning(): Flow<V2XWarning> {
        return repositories.getWarning()
    }

    fun getInform(): Flow<V2XInform> {
        return repositories.getInform()
    }

    fun getVehicle(): Flow<V2XVehicleStatus> {
        return repositories.getVehicle()
    }

    fun getSPaT(): Flow<V2XSPaT> {
        return repositories.getSPaT()
    }
}