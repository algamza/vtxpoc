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

    fun getTrackingObj1(): Flow<V2XTrackingObj> {
        return repositories.getTrackingObj1()
    }

    fun getTrackingObj2(): Flow<V2XTrackingObj> {
        return repositories.getTrackingObj2()
    }

    fun getTrackingObj3(): Flow<V2XTrackingObj> {
        return repositories.getTrackingObj3()
    }

    fun getWow(): Flow<V2XWow> {
        return repositories.getWow()
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

    fun callbackTrackingObj1(): Flow<V2XTrackingObj> {
        return repositories.callbackTrackingObj1()
    }

    fun callbackTrackingObj2(): Flow<V2XTrackingObj> {
        return repositories.callbackTrackingObj2()
    }

    fun callbackTrackingObj3(): Flow<V2XTrackingObj> {
        return repositories.callbackTrackingObj3()
    }

    fun callbackWow(): Flow<V2XWow> {
        return repositories.callbackWow()
    }
}