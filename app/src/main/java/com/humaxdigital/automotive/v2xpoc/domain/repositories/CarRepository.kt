package com.humaxdigital.automotive.v2xpoc.domain.repositories

import com.humaxdigital.automotive.v2xpoc.domain.entities.*
import kotlinx.coroutines.flow.Flow

interface CarRepository {
    fun getWarning() : Flow<V2XWarnInform>
    fun getInform() : Flow<V2XWarnInform>
    fun getVehicleStatus() : Flow<V2XVehicleStatus>
    fun getSPaT() : Flow<V2XSPaT>
    fun getHVPos() : Flow<V2XHVPos>
    fun getHVMotion() : Flow<V2XHVMotion>
    fun getRV1Status() : Flow<V2XRSUStatus>
    fun getRV2Status() : Flow<V2XRSUStatus>
    fun getRV3Status() : Flow<V2XRSUStatus>
    fun getRV4Status() : Flow<V2XRSUStatus>
    fun getTrackingObj1() : Flow<V2XTrackingObj>
    fun getTrackingObj2() : Flow<V2XTrackingObj>
    fun getTrackingObj3() : Flow<V2XTrackingObj>
    fun getWow() : Flow<V2XWow>
    fun setWow(wow: Flow<V2XWow>)

    fun callbackWarning() : Flow<V2XWarnInform>
    fun callbackInform() : Flow<V2XWarnInform>
    fun callbackVehicleStatus() : Flow<V2XVehicleStatus>
    fun callbackSPaT() : Flow<V2XSPaT>
    fun callbackHVPos() : Flow<V2XHVPos>
    fun callbackHVMotion() : Flow<V2XHVMotion>
    fun callbackRV1Status() : Flow<V2XRSUStatus>
    fun callbackRV2Status() : Flow<V2XRSUStatus>
    fun callbackRV3Status() : Flow<V2XRSUStatus>
    fun callbackRV4Status() : Flow<V2XRSUStatus>
    fun callbackTrackingObj1() : Flow<V2XTrackingObj>
    fun callbackTrackingObj2() : Flow<V2XTrackingObj>
    fun callbackTrackingObj3() : Flow<V2XTrackingObj>
    fun callbackWow() : Flow<V2XWow>
}