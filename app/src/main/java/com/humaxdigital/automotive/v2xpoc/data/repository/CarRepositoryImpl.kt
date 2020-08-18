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
        return api.getRV1Status().map { CarSignalMapper().mapToEntity(it) }
    }

    override fun getRV2Status(): Flow<V2XRSUStatus> {
        return api.getRV2Status().map { CarSignalMapper().mapToEntity(it) }
    }

    override fun getRV3Status(): Flow<V2XRSUStatus> {
        return api.getRV3Status().map { CarSignalMapper().mapToEntity(it) }
    }

    override fun getRV4Status(): Flow<V2XRSUStatus> {
        return api.getRV4Status().map { CarSignalMapper().mapToEntity(it) }
    }

    override fun getTrackingObj1(): Flow<V2XTrackingObj> {
        return api.getTrackingObj1().map { CarSignalMapper().mapToEntity(it) }
    }

    override fun getTrackingObj2(): Flow<V2XTrackingObj> {
        return api.getTrackingObj2().map { CarSignalMapper().mapToEntity(it) }
    }

    override fun getTrackingObj3(): Flow<V2XTrackingObj> {
        return api.getTrackingObj3().map { CarSignalMapper().mapToEntity(it) }
    }

    override fun getWow(): Flow<V2XWow> {
        return api.getExt().map { CarSignalMapper().mapToEntity(it) }
    }

    @kotlinx.coroutines.ExperimentalCoroutinesApi
    override fun callbackWarning(): Flow<V2XWarnInform> {
        return api.callbackWarning().map { CarSignalMapper().mapToEntity(it) }
    }

    @kotlinx.coroutines.ExperimentalCoroutinesApi
    override fun callbackInform(): Flow<V2XWarnInform> {
        return api.callbackInform().map { CarSignalMapper().mapToEntity(it) }
    }

    @kotlinx.coroutines.ExperimentalCoroutinesApi
    override fun callbackVehicleStatus(): Flow<V2XVehicleStatus> {
        return api.callbackVehicleStatus().map { CarSignalMapper().mapToEntity(it) }
    }

    @kotlinx.coroutines.ExperimentalCoroutinesApi
    override fun callbackSPaT(): Flow<V2XSPaT> {
        return api.callbackSPaT().map { CarSignalMapper().mapToEntity(it) }
    }

    @kotlinx.coroutines.ExperimentalCoroutinesApi
    override fun callbackHVPos(): Flow<V2XHVPos> {
        return api.callbackHVPos().map { CarSignalMapper().mapToEntity(it) }
    }

    @kotlinx.coroutines.ExperimentalCoroutinesApi
    override fun callbackHVMotion(): Flow<V2XHVMotion> {
        return api.callbackHVMotion().map { CarSignalMapper().mapToEntity(it) }
    }

    @kotlinx.coroutines.ExperimentalCoroutinesApi
    override fun callbackRV1Status(): Flow<V2XRSUStatus> {
        return api.callbackRV1Status().map { CarSignalMapper().mapToEntity(it) }
    }

    @kotlinx.coroutines.ExperimentalCoroutinesApi
    override fun callbackRV2Status(): Flow<V2XRSUStatus> {
        return api.callbackRV2Status().map { CarSignalMapper().mapToEntity(it) }
    }

    @kotlinx.coroutines.ExperimentalCoroutinesApi
    override fun callbackRV3Status(): Flow<V2XRSUStatus> {
        return api.callbackRV3Status().map { CarSignalMapper().mapToEntity(it) }
    }

    @kotlinx.coroutines.ExperimentalCoroutinesApi
    override fun callbackRV4Status(): Flow<V2XRSUStatus> {
        return api.callbackRV4Status().map { CarSignalMapper().mapToEntity(it) }
    }

    @kotlinx.coroutines.ExperimentalCoroutinesApi
    override fun callbackTrackingObj1(): Flow<V2XTrackingObj> {
        return api.callbackTrackingObj1().map { CarSignalMapper().mapToEntity(it) }
    }

    @kotlinx.coroutines.ExperimentalCoroutinesApi
    override fun callbackTrackingObj2(): Flow<V2XTrackingObj> {
        return api.callbackTrackingObj2().map { CarSignalMapper().mapToEntity(it) }
    }

    @kotlinx.coroutines.ExperimentalCoroutinesApi
    override fun callbackTrackingObj3(): Flow<V2XTrackingObj> {
        return api.callbackTrackingObj3().map { CarSignalMapper().mapToEntity(it) }
    }

    @kotlinx.coroutines.ExperimentalCoroutinesApi
    override fun callbackWow(): Flow<V2XWow> {
        return api.callbackExt().map { CarSignalMapper().mapToEntity(it) }
    }
}