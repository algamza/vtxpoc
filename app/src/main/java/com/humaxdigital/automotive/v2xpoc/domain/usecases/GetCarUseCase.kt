package com.humaxdigital.automotive.v2xpoc.domain.usecases

import com.humaxdigital.automotive.v2xpoc.domain.entities.*
import com.humaxdigital.automotive.v2xpoc.domain.repositories.CarRepository
import kotlinx.coroutines.flow.Flow

class GetCarUseCase(private val repositories: CarRepository) {

    fun getWarning(): Flow<V2XWarnInform> {
        return repositories.getWarning()
    }

    fun getInform(): Flow<V2XWarnInform> {
        return repositories.getInform()
    }

    fun getVehicleStatus(): Flow<V2XVehicleStatus> {
        return repositories.getVehicleStatus()
    }

    fun getSPaT(): Flow<V2XSPaT> {
        return repositories.getSPaT()
    }

    fun getHVPos(): Flow<V2XHVPos> {
        return repositories.getHVPos()
    }

    fun getHVMotion(): Flow<V2XHVMotion> {
        return repositories.getHVMotion()
    }

    fun getRV1Status(): Flow<V2XRSUStatus> {
        return repositories.getRV1Status()
    }

    fun getRV2Status(): Flow<V2XRSUStatus> {
        return repositories.getRV2Status()
    }

    fun getRV3Status(): Flow<V2XRSUStatus> {
        return repositories.getRV3Status()
    }

    fun getRV4Status(): Flow<V2XRSUStatus> {
        return repositories.getRV4Status()
    }

    fun callbackWarning(): Flow<V2XWarnInform> {
        return repositories.callbackWarning()
    }

    fun callbackInform(): Flow<V2XWarnInform> {
        return repositories.callbackInform()
    }

    fun callbackVehicleStatus(): Flow<V2XVehicleStatus> {
        return repositories.callbackVehicleStatus()
    }

    fun callbackSPaT(): Flow<V2XSPaT> {
        return repositories.callbackSPaT()
    }

    fun callbackHVPos(): Flow<V2XHVPos> {
        return repositories.callbackHVPos()
    }

    fun callbackHVMotion(): Flow<V2XHVMotion> {
        return repositories.callbackHVMotion()
    }

    fun callbackRV1Status(): Flow<V2XRSUStatus> {
        return repositories.callbackRV1Status()
    }

    fun callbackRV2Status(): Flow<V2XRSUStatus> {
        return repositories.callbackRV2Status()
    }

    fun callbackRV3Status(): Flow<V2XRSUStatus> {
        return repositories.callbackRV3Status()
    }

    fun callbackRV4Status(): Flow<V2XRSUStatus> {
        return repositories.callbackRV4Status()
    }
}