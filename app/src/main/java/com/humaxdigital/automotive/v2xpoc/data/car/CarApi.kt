package com.humaxdigital.automotive.v2xpoc.data.car

import android.car.VehicleAreaType
import android.car.hardware.CarPropertyValue
import android.car.hardware.property.CarPropertyManager
import android.extension.car.CarEx
import android.extension.car.CarTMSManager
import android.util.Log
import com.humaxdigital.automotive.v2xpoc.data.entities.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.sendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import java.lang.Exception
import kotlin.experimental.and


class CarApi(car: CarEx) {
    private val TAG = "CarApi"

    private val ID_PROPERTY_V2X : Int = 779091989

    private val ID_AREA_V2X_HV_MOTION = 0
    private val ID_AREA_V2X_HV_POS = 1
    private val ID_AREA_V2X_INFORM = 2
    private val ID_AREA_V2X_RV1_STATUS = 3
    private val ID_AREA_V2X_RV2_STATUS = 4
    private val ID_AREA_V2X_RV3_STATUS = 5
    private val ID_AREA_V2X_RV4_STATUS = 6
    private val ID_AREA_V2X_SPAT = 7
    private val ID_AREA_V2X_VEHICLE_STATUS = 8
    private val ID_AREA_V2X_WARNING = 9

    private var callback_warning : ((WarnInform) -> Unit)? = null
    private var callback_inform : ((WarnInform) -> Unit)? = null
    private var callback_vehicle : ((VehicleStatus) -> Unit)? = null
    private var callback_spat : ((SPaT) -> Unit)? = null
    private var callback_hvpos : ((HVPos) -> Unit)? = null
    private var callback_hvmotion : ((HVMotion) -> Unit)? = null
    private var callback_rv1 : ((RSUStatus) -> Unit)? = null
    private var callback_rv2 : ((RSUStatus) -> Unit)? = null
    private var callback_rv3 : ((RSUStatus) -> Unit)? = null
    private var callback_rv4 : ((RSUStatus) -> Unit)? = null

    private val _car = car

    init {
        GlobalScope.launch {
            while (!_car.isConnected) {}
            var manager = _car.getCarManager(android.car.Car.PROPERTY_SERVICE) as CarPropertyManager
            var callback = object : CarPropertyManager.CarPropertyEventListener {
                override fun onChangeEvent(p0: CarPropertyValue<*>) {
                    try {
                        when(p0.areaId) {
                            ID_AREA_V2X_WARNING -> { callback_warning!!(CarSignalParser().parseAsWarnInform(p0.value as ByteArray)) }
                            ID_AREA_V2X_INFORM -> { callback_inform!!(CarSignalParser().parseAsWarnInform(p0.value as ByteArray)) }
                            ID_AREA_V2X_VEHICLE_STATUS -> { callback_vehicle!!(CarSignalParser().parseAsVehicleStatus(p0.value as ByteArray)) }
                            ID_AREA_V2X_SPAT -> { callback_spat!!(CarSignalParser().parseAsSPaT(p0.value as ByteArray)) }
                            ID_AREA_V2X_HV_POS -> { callback_hvpos!!(CarSignalParser().parseAsHVPos(p0.value as ByteArray)) }
                            ID_AREA_V2X_HV_MOTION -> { callback_hvmotion!!(CarSignalParser().parseAsHVMotion(p0.value as ByteArray)) }
                            ID_AREA_V2X_RV1_STATUS -> { callback_rv1!!(CarSignalParser().parseAsRSUStatus(p0.value as ByteArray)) }
                            ID_AREA_V2X_RV2_STATUS -> { callback_rv2!!(CarSignalParser().parseAsRSUStatus(p0.value as ByteArray)) }
                            ID_AREA_V2X_RV3_STATUS -> { callback_rv3!!(CarSignalParser().parseAsRSUStatus(p0.value as ByteArray)) }
                            ID_AREA_V2X_RV4_STATUS -> { callback_rv4!!(CarSignalParser().parseAsRSUStatus(p0.value as ByteArray)) }
                        }
                    } catch (e:Exception) {
                        Log.e(TAG, e.toString())
                    }
                }
                override fun onErrorEvent(p0: Int, p1: Int) {
                    Log.d(TAG, "callbackWarning=error")
                }
            }
            manager.registerListener(callback, ID_PROPERTY_V2X, 0.0f)
        }
    }

    fun getWarning() = flow<WarnInform> {
        while (!_car.isConnected) {}
        try {
            var manager = _car.getCarManager(android.car.Car.PROPERTY_SERVICE) as CarPropertyManager
            var value = manager.getProperty<ByteArray>(ID_PROPERTY_V2X, ID_AREA_V2X_WARNING)
            var warning = CarSignalParser().parseAsWarnInform(value!!.value as ByteArray)
            Log.d(TAG, "getWarning="+warning.toString())
            emit(warning)
        } catch (e:Exception) {
            Log.e(TAG, e.toString())
        }
    }

    fun getInform() = flow<WarnInform> {
        while (!_car.isConnected) {}
        try {
            var manager = _car.getCarManager(android.car.Car.PROPERTY_SERVICE) as CarPropertyManager
            var value = manager.getProperty<ByteArray>(ID_PROPERTY_V2X, ID_AREA_V2X_INFORM)
            var inform = CarSignalParser().parseAsWarnInform(value!!.value as ByteArray)
            Log.d(TAG, "getInform="+inform.toString())
            emit(inform)
        } catch (e:Exception) {
            Log.e(TAG, e.toString())
        }
    }

    fun getVehicleStatus() = flow<VehicleStatus> {
        while (!_car.isConnected) {}
        try {
            var manager = _car.getCarManager(android.car.Car.PROPERTY_SERVICE) as CarPropertyManager
            var value = manager.getProperty<ByteArray>(ID_PROPERTY_V2X, ID_AREA_V2X_VEHICLE_STATUS)
            var status = CarSignalParser().parseAsVehicleStatus(value!!.value as ByteArray)
            Log.d(TAG, "getVehicleStatus="+status.toString())
            emit(status)
        } catch (e:Exception) {
            Log.e(TAG, e.toString())
        }
    }

    fun getSPaT() = flow<SPaT> {
        while (!_car.isConnected) {}
        try {
            var manager = _car.getCarManager(android.car.Car.PROPERTY_SERVICE) as CarPropertyManager
            var value = manager.getProperty<ByteArray>(ID_PROPERTY_V2X, ID_AREA_V2X_SPAT)
            var spat = CarSignalParser().parseAsSPaT(value!!.value as ByteArray)
            Log.d(TAG, "getSPaT="+spat.toString())
            emit(spat)
        } catch (e:Exception) {
            Log.e(TAG, e.toString())
        }
    }

    fun getHVPos() = flow<HVPos> {
        while (!_car.isConnected) {}
        try {
            var manager = _car.getCarManager(android.car.Car.PROPERTY_SERVICE) as CarPropertyManager
            var value = manager.getProperty<ByteArray>(ID_PROPERTY_V2X, ID_AREA_V2X_HV_POS)
            var hvpos = CarSignalParser().parseAsHVPos(value!!.value as ByteArray)
            Log.d(TAG, "getHVPos="+hvpos.toString())
            emit(hvpos)
        } catch (e:Exception) {
            Log.e(TAG, e.toString())
        }
    }

    fun getHVMotion() = flow<HVMotion> {
        while (!_car.isConnected) {}
        try {
            var manager = _car.getCarManager(android.car.Car.PROPERTY_SERVICE) as CarPropertyManager
            var value = manager.getProperty<ByteArray>(ID_PROPERTY_V2X, ID_AREA_V2X_HV_MOTION)
            var hvmotion = CarSignalParser().parseAsHVMotion(value!!.value as ByteArray)
            Log.d(TAG, "getHVMotion="+hvmotion.toString())
            emit(hvmotion)
        } catch (e:Exception) {
            Log.e(TAG, e.toString())
        }
    }

    fun getRV1Status() = flow<RSUStatus> {
        while (!_car.isConnected) {}
        try {
            var manager = _car.getCarManager(android.car.Car.PROPERTY_SERVICE) as CarPropertyManager
            var value = manager.getProperty<ByteArray>(ID_PROPERTY_V2X, ID_AREA_V2X_RV1_STATUS)
            var rv = CarSignalParser().parseAsRSUStatus(value!!.value as ByteArray)
            Log.d(TAG, "getRV1Status="+rv.toString())
            emit(rv)
        } catch (e:Exception) {
            Log.e(TAG, e.toString())
        }
    }

    fun getRV2Status() = flow<RSUStatus> {
        while (!_car.isConnected) {}
        try {
            var manager = _car.getCarManager(android.car.Car.PROPERTY_SERVICE) as CarPropertyManager
            var value = manager.getProperty<ByteArray>(ID_PROPERTY_V2X, ID_AREA_V2X_RV2_STATUS)
            var rv = CarSignalParser().parseAsRSUStatus(value!!.value as ByteArray)
            Log.d(TAG, "getRV2Status="+rv.toString())
            emit(rv)
        } catch (e:Exception) {
            Log.e(TAG, e.toString())
        }
    }

    fun getRV3Status() = flow<RSUStatus> {
        while (!_car.isConnected) {}
        try {
            var manager = _car.getCarManager(android.car.Car.PROPERTY_SERVICE) as CarPropertyManager
            var value = manager.getProperty<ByteArray>(ID_PROPERTY_V2X, ID_AREA_V2X_RV3_STATUS)
            var rv = CarSignalParser().parseAsRSUStatus(value!!.value as ByteArray)
            Log.d(TAG, "getRV3Status="+rv.toString())
            emit(rv)
        } catch (e:Exception) {
            Log.e(TAG, e.toString())
        }
    }

    fun getRV4Status() = flow<RSUStatus> {
        while (!_car.isConnected) {}
        try {
            var manager = _car.getCarManager(android.car.Car.PROPERTY_SERVICE) as CarPropertyManager
            var value = manager.getProperty<ByteArray>(ID_PROPERTY_V2X, ID_AREA_V2X_RV4_STATUS)
            var rv = CarSignalParser().parseAsRSUStatus(value!!.value as ByteArray)
            Log.d(TAG, "getRV4Status="+rv.toString())
            emit(rv)
        } catch (e:Exception) {
            Log.e(TAG, e.toString())
        }
    }

    @kotlinx.coroutines.ExperimentalCoroutinesApi
    fun callbackWarning() = callbackFlow<WarnInform> {
        setWarningCallback { sendBlocking(it) }
        awaitClose { }
    }

    @kotlinx.coroutines.ExperimentalCoroutinesApi
    fun callbackInform() = callbackFlow<WarnInform> {
        setInformCallback { sendBlocking(it) }
        awaitClose { }
    }

    @kotlinx.coroutines.ExperimentalCoroutinesApi
    fun callbackVehicleStatus() = callbackFlow<VehicleStatus> {
        setVehicleCallback { sendBlocking(it) }
        awaitClose { }
    }

    @kotlinx.coroutines.ExperimentalCoroutinesApi
    fun callbackSPaT() = callbackFlow<SPaT> {
        setSPaTCallback { sendBlocking(it) }
        awaitClose { }
    }

    @kotlinx.coroutines.ExperimentalCoroutinesApi
    fun callbackHVPos() = callbackFlow<HVPos> {
        setHVPosCallback { sendBlocking(it) }
        awaitClose { }
    }

    @kotlinx.coroutines.ExperimentalCoroutinesApi
    fun callbackHVMotion() = callbackFlow<HVMotion> {
        setHVMotionCallback { sendBlocking(it) }
        awaitClose { }
    }

    @kotlinx.coroutines.ExperimentalCoroutinesApi
    fun callbackRV1Status() = callbackFlow<RSUStatus> {
        setRV1Callback { sendBlocking(it) }
        awaitClose { }
    }

    @kotlinx.coroutines.ExperimentalCoroutinesApi
    fun callbackRV2Status() = callbackFlow<RSUStatus> {
        setRV2Callback { sendBlocking(it) }
        awaitClose { }
    }

    @kotlinx.coroutines.ExperimentalCoroutinesApi
    fun callbackRV3Status() = callbackFlow<RSUStatus> {
        setRV3Callback { sendBlocking(it) }
        awaitClose { }
    }

    @kotlinx.coroutines.ExperimentalCoroutinesApi
    fun callbackRV4Status() = callbackFlow<RSUStatus> {
        setRV4Callback { sendBlocking(it) }
        awaitClose { }
    }

    private fun setWarningCallback(obj:(WarnInform)->Unit) { callback_warning = obj }
    private fun setInformCallback(obj:(WarnInform)->Unit) { callback_inform = obj }
    private fun setVehicleCallback(obj:(VehicleStatus)->Unit) { callback_vehicle = obj }
    private fun setSPaTCallback(obj:(SPaT)->Unit) { callback_spat = obj }
    private fun setHVPosCallback(obj:(HVPos)->Unit) { callback_hvpos = obj }
    private fun setHVMotionCallback(obj:(HVMotion)->Unit) { callback_hvmotion = obj }
    private fun setRV1Callback(obj:(RSUStatus)->Unit) { callback_rv1 = obj }
    private fun setRV2Callback(obj:(RSUStatus)->Unit) { callback_rv2 = obj }
    private fun setRV3Callback(obj:(RSUStatus)->Unit) { callback_rv3 = obj }
    private fun setRV4Callback(obj:(RSUStatus)->Unit) { callback_rv4 = obj }
}