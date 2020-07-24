package com.humaxdigital.automotive.v2xpoc.data.repository

import com.humaxdigital.automotive.v2xpoc.data.car.CarApi
import com.humaxdigital.automotive.v2xpoc.data.entities.CarSignalMapper
import com.humaxdigital.automotive.v2xpoc.domain.entities.*
import com.humaxdigital.automotive.v2xpoc.domain.repositories.CarRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CarRepositoryImpl constructor(private val api : CarApi) : CarRepository {
    override fun getWarning(): Flow<V2XWarnInform> {
        return api.getWarning().map { CarSignalMapper().mapToEntity(it) }
    }

    override fun getInform(): Flow<V2XWarnInform> {
        return api.getInform().map { CarSignalMapper().mapToEntity(it) }
    }

    override fun getVehicleStatus(): Flow<V2XVehicleStatus> {
        return api.getVehicleStatus().map { CarSignalMapper().mapToEntity(it) }
    }

    override fun getSPaT(): Flow<V2XSPaT> {
        return api.getSPaT().map { CarSignalMapper().mapToEntity(it) }
    }

    override fun getHVPos(): Flow<V2XHVPos> {
        return api.getHVPos().map { CarSignalMapper().mapToEntity(it) }
    }

    override fun getHVMotion(): Flow<V2XHVMotion> {
        return api.getHVMotion().map { CarSignalMapper().mapToEntity(it) }
    }

    override fun getRV1Status(): Flow<V2XRSUStatus> {
        return api.getRV4Status().map { CarSignalMapper().mapToEntity(it) }
    }

    override fun getRV2Status(): Flow<V2XRSUStatus> {
        return api.getRV4Status().map { CarSignalMapper().mapToEntity(it) }
    }

    override fun getRV3Status(): Flow<V2XRSUStatus> {
        return api.getRV4Status().map { CarSignalMapper().mapToEntity(it) }
    }

    override fun getRV4Status(): Flow<V2XRSUStatus> {
        return api.getRV4Status().map { CarSignalMapper().mapToEntity(it) }
    }

    override fun callbackWarning(): Flow<V2XWarnInform> {
        return api.callbackWarning().map { CarSignalMapper().mapToEntity(it) }
    }

    override fun callbackInform(): Flow<V2XWarnInform> {
        return api.callbackInform().map { CarSignalMapper().mapToEntity(it) }
    }

    override fun callbackVehicleStatus(): Flow<V2XVehicleStatus> {
        return api.callbackVehicleStatus().map { CarSignalMapper().mapToEntity(it) }
    }

    override fun callbackSPaT(): Flow<V2XSPaT> {
        return api.callbackSPaT().map { CarSignalMapper().mapToEntity(it) }
    }

    override fun callbackHVPos(): Flow<V2XHVPos> {
        return api.callbackHVPos().map { CarSignalMapper().mapToEntity(it) }
    }

    override fun callbackHVMotion(): Flow<V2XHVMotion> {
        return api.callbackHVMotion().map { CarSignalMapper().mapToEntity(it) }
    }

    override fun callbackRV1Status(): Flow<V2XRSUStatus> {
        return api.callbackRV1Status().map { CarSignalMapper().mapToEntity(it) }
    }

    override fun callbackRV2Status(): Flow<V2XRSUStatus> {
        return api.callbackRV2Status().map { CarSignalMapper().mapToEntity(it) }
    }

    override fun callbackRV3Status(): Flow<V2XRSUStatus> {
        return api.callbackRV3Status().map { CarSignalMapper().mapToEntity(it) }
    }

    override fun callbackRV4Status(): Flow<V2XRSUStatus> {
        return api.callbackRV4Status().map { CarSignalMapper().mapToEntity(it) }
    }
}