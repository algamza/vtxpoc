package com.humaxdigital.automotive.v2xpoc.data.repository

import com.humaxdigital.automotive.v2xpoc.data.car.CarApi
import com.humaxdigital.automotive.v2xpoc.data.entities.CarSignalMapper
import com.humaxdigital.automotive.v2xpoc.domain.entities.*
import com.humaxdigital.automotive.v2xpoc.domain.repositories.CarRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CarRepositoryImpl constructor(private val api : CarApi) : CarRepository {
    override fun getWarning(): Flow<V2XWarning> {
        return api.getWarning().map {CarSignalMapper().mapToEntity(it)}
    }

    override fun getInform(): Flow<V2XInform> {
        return api.getInform().map { CarSignalMapper().mapToEntity(it)}
    }

    override fun getVehicle(): Flow<V2XVehicleStatus> {
        return api.getVehicle().map { CarSignalMapper().mapToEntity(it)}
    }

    override fun getSPaT(): Flow<V2XSPaT> {
        return api.getSPaT().map { CarSignalMapper().mapToEntity(it)}
    }
}