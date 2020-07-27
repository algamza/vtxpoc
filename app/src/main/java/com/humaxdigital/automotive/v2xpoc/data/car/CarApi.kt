package com.humaxdigital.automotive.v2xpoc.data.car

import android.car.VehicleAreaType
import android.car.hardware.CarPropertyValue
import android.car.hardware.property.CarPropertyManager
import android.extension.car.CarEx
import android.extension.car.CarTMSManager
import android.util.Log
import com.humaxdigital.automotive.v2xpoc.data.entities.*
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.sendBlocking
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import kotlin.experimental.and


class CarApi(car: CarEx) {
    private val TAG = "CarApi"

    private val ID_PROPERTY_V2X_WARNING : Int = 0x0000//0x11400400 // SENSOR_TYPE_GEAR
    private val ID_PROPERTY_V2X_INFORM : Int = 0x0002//561029124 // VENDOR_TMS_EVENT
    private val ID_PROPERTY_V2X_SPAT : Int = 0x0002
    private val ID_PROPERTY_V2X_STATUS : Int = 0x0003
    private val ID_PROPERTY_V2X_HVPOS : Int = 0x0004
    private val ID_PROPERTY_V2X_HVMOTION : Int = 0x0005
    private val ID_PROPERTY_V2X_RV1 : Int = 0x0006
    private val ID_PROPERTY_V2X_RV2 : Int = 0x0007
    private val ID_PROPERTY_V2X_RV3 : Int = 0x0008
    private val ID_PROPERTY_V2X_RV4 : Int = 0x0009

    private val _car = car

    init { }

    fun getWarning() = flow<WarnInform> {
        while (!_car.isConnected) {}
        try {
            var manager = _car.getCarManager(android.car.Car.PROPERTY_SERVICE) as CarPropertyManager
            var value = manager.getProperty<ByteArray>(
                ID_PROPERTY_V2X_WARNING,
                VehicleAreaType.VEHICLE_AREA_TYPE_GLOBAL)
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
            var value = manager.getProperty<ByteArray>(
                ID_PROPERTY_V2X_INFORM,
                VehicleAreaType.VEHICLE_AREA_TYPE_GLOBAL)
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
            var value = manager.getProperty<ByteArray>(
                ID_PROPERTY_V2X_STATUS,
                VehicleAreaType.VEHICLE_AREA_TYPE_GLOBAL)
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
            var value = manager.getProperty<ByteArray>(
                ID_PROPERTY_V2X_SPAT,
                VehicleAreaType.VEHICLE_AREA_TYPE_GLOBAL)
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
            var value = manager.getProperty<ByteArray>(
                ID_PROPERTY_V2X_HVPOS,
                VehicleAreaType.VEHICLE_AREA_TYPE_GLOBAL)
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
            var value = manager.getProperty<ByteArray>(
                ID_PROPERTY_V2X_HVMOTION,
                VehicleAreaType.VEHICLE_AREA_TYPE_GLOBAL)
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
            var value = manager.getProperty<ByteArray>(
                ID_PROPERTY_V2X_RV1,
                VehicleAreaType.VEHICLE_AREA_TYPE_GLOBAL)
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
            var value = manager.getProperty<ByteArray>(
                ID_PROPERTY_V2X_RV2,
                VehicleAreaType.VEHICLE_AREA_TYPE_GLOBAL)
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
            var value = manager.getProperty<ByteArray>(
                ID_PROPERTY_V2X_RV3,
                VehicleAreaType.VEHICLE_AREA_TYPE_GLOBAL)
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
            var value = manager.getProperty<ByteArray>(
                ID_PROPERTY_V2X_RV4,
                VehicleAreaType.VEHICLE_AREA_TYPE_GLOBAL)
            var rv = CarSignalParser().parseAsRSUStatus(value!!.value as ByteArray)
            Log.d(TAG, "getRV4Status="+rv.toString())
            emit(rv)
        } catch (e:Exception) {
            Log.e(TAG, e.toString())
        }
    }

    fun callbackWarning() = callbackFlow<WarnInform> {
        while (!_car.isConnected) {}
        var manager = _car.getCarManager(android.car.Car.PROPERTY_SERVICE) as CarPropertyManager
        var callback = object : CarPropertyManager.CarPropertyEventListener {
            override fun onChangeEvent(p0: CarPropertyValue<*>?) {
                try {
                    var warning = CarSignalParser().parseAsWarnInform(p0!!.value as ByteArray)
                    Log.d(TAG, "callbackWarning="+warning.toString())
                    sendBlocking(warning)
                } catch (e:Exception) {
                    Log.e(TAG, e.toString())
                }
            }
            override fun onErrorEvent(p0: Int, p1: Int) {
                Log.d(TAG, "callbackWarning=error")
            }
        }
        manager.registerListener(callback, ID_PROPERTY_V2X_WARNING, 0.0f)
        awaitClose { manager.unregisterListener(callback) }
    }

    fun callbackInform() = callbackFlow<WarnInform> {
        while (!_car.isConnected) {}
        var manager = _car.getCarManager(android.car.Car.PROPERTY_SERVICE) as CarPropertyManager
        var callback = object : CarPropertyManager.CarPropertyEventListener {
            override fun onChangeEvent(p0: CarPropertyValue<*>?) {
                try {
                    var inform = CarSignalParser().parseAsWarnInform(p0!!.value as ByteArray)
                    Log.d(TAG, "callbackInform="+inform.toString())
                    sendBlocking(inform)
                } catch (e:Exception) {
                    Log.e(TAG, e.toString())
                }
            }
            override fun onErrorEvent(p0: Int, p1: Int) {
                Log.d(TAG, "callbackInform=error")
            }
        }
        manager.registerListener(callback, ID_PROPERTY_V2X_INFORM, 0.0f)
        awaitClose { manager.unregisterListener(callback) }
    }

    fun callbackVehicleStatus() = callbackFlow<VehicleStatus> {
        while (!_car.isConnected) {}
        var manager = _car.getCarManager(android.car.Car.PROPERTY_SERVICE) as CarPropertyManager
        var callback = object : CarPropertyManager.CarPropertyEventListener {
            override fun onChangeEvent(p0: CarPropertyValue<*>?) {
                try {
                    var status = CarSignalParser().parseAsVehicleStatus(p0!!.value as ByteArray)
                    Log.d(TAG, "callbackVehicleStatus="+status.toString())
                    sendBlocking(status)
                } catch (e:Exception) {
                    Log.e(TAG, e.toString())
                }
            }
            override fun onErrorEvent(p0: Int, p1: Int) {
                Log.d(TAG, "callbackVehicleStatus=error")
            }
        }
        manager.registerListener(callback, ID_PROPERTY_V2X_STATUS, 0.0f)
        awaitClose { manager.unregisterListener(callback) }
    }

    fun callbackSPaT() = callbackFlow<SPaT> {
        while (!_car.isConnected) {}
        var manager = _car.getCarManager(android.car.Car.PROPERTY_SERVICE) as CarPropertyManager
        var callback = object : CarPropertyManager.CarPropertyEventListener {
            override fun onChangeEvent(p0: CarPropertyValue<*>?) {
                try {
                    var spat = CarSignalParser().parseAsSPaT(p0!!.value as ByteArray)
                    Log.d(TAG, "callbackSPaT="+spat.toString())
                    sendBlocking(spat)
                } catch (e:Exception) {
                    Log.e(TAG, e.toString())
                }
            }
            override fun onErrorEvent(p0: Int, p1: Int) {
                Log.d(TAG, "callbackSPaT=error")
            }
        }
        manager.registerListener(callback, ID_PROPERTY_V2X_SPAT, 0.0f)
        awaitClose { manager.unregisterListener(callback) }
    }

    fun callbackHVPos() = callbackFlow<HVPos> {
        while (!_car.isConnected) {}
        var manager = _car.getCarManager(android.car.Car.PROPERTY_SERVICE) as CarPropertyManager
        var callback = object : CarPropertyManager.CarPropertyEventListener {
            override fun onChangeEvent(p0: CarPropertyValue<*>?) {
                try {
                    var pos = CarSignalParser().parseAsHVPos(p0!!.value as ByteArray)
                    Log.d(TAG, "callbackHVPos="+pos.toString())
                    sendBlocking(pos)
                } catch (e:Exception) {
                    Log.e(TAG, e.toString())
                }
            }
            override fun onErrorEvent(p0: Int, p1: Int) {
                Log.d(TAG, "callbackHVPos=error")
            }
        }
        manager.registerListener(callback, ID_PROPERTY_V2X_HVPOS, 0.0f)
        awaitClose { manager.unregisterListener(callback) }
    }

    fun callbackHVMotion() = callbackFlow<HVMotion> {
        while (!_car.isConnected) {}
        var manager = _car.getCarManager(android.car.Car.PROPERTY_SERVICE) as CarPropertyManager
        var callback = object : CarPropertyManager.CarPropertyEventListener {
            override fun onChangeEvent(p0: CarPropertyValue<*>?) {
                try {
                    var motion = CarSignalParser().parseAsHVMotion(p0!!.value as ByteArray)
                    Log.d(TAG, "callbackHVMotion="+motion.toString())
                    sendBlocking(motion)
                } catch (e:Exception) {
                    Log.e(TAG, e.toString())
                }
            }
            override fun onErrorEvent(p0: Int, p1: Int) {
                Log.d(TAG, "callbackHVMotion=error")
            }
        }
        manager.registerListener(callback, ID_PROPERTY_V2X_HVMOTION, 0.0f)
        awaitClose { manager.unregisterListener(callback) }
    }

    fun callbackRV1Status() = callbackFlow<RSUStatus> {
        while (!_car.isConnected) {}
        var manager = _car.getCarManager(android.car.Car.PROPERTY_SERVICE) as CarPropertyManager
        var callback = object : CarPropertyManager.CarPropertyEventListener {
            override fun onChangeEvent(p0: CarPropertyValue<*>?) {
                try {
                    var rv = CarSignalParser().parseAsRSUStatus(p0!!.value as ByteArray)
                    Log.d(TAG, "callbackRV1Status="+rv.toString())
                    sendBlocking(rv)
                } catch (e:Exception) {
                    Log.e(TAG, e.toString())
                }
            }
            override fun onErrorEvent(p0: Int, p1: Int) {
                Log.d(TAG, "callbackRV1Status=error")
            }
        }
        manager.registerListener(callback, ID_PROPERTY_V2X_RV1, 0.0f)
        awaitClose { manager.unregisterListener(callback) }
    }

    fun callbackRV2Status() = callbackFlow<RSUStatus> {
        while (!_car.isConnected) {}
        var manager = _car.getCarManager(android.car.Car.PROPERTY_SERVICE) as CarPropertyManager
        var callback = object : CarPropertyManager.CarPropertyEventListener {
            override fun onChangeEvent(p0: CarPropertyValue<*>?) {
                try {
                    var rv = CarSignalParser().parseAsRSUStatus(p0!!.value as ByteArray)
                    Log.d(TAG, "callbackRV2Status="+rv.toString())
                    sendBlocking(rv)
                } catch (e:Exception) {
                    Log.e(TAG, e.toString())
                }
            }
            override fun onErrorEvent(p0: Int, p1: Int) {
                Log.d(TAG, "callbackRV3Status=error")
            }
        }
        manager.registerListener(callback, ID_PROPERTY_V2X_RV2, 0.0f)
        awaitClose { manager.unregisterListener(callback) }
    }

    fun callbackRV3Status() = callbackFlow<RSUStatus> {
        while (!_car.isConnected) {}
        var manager = _car.getCarManager(android.car.Car.PROPERTY_SERVICE) as CarPropertyManager
        var callback = object : CarPropertyManager.CarPropertyEventListener {
            override fun onChangeEvent(p0: CarPropertyValue<*>?) {
                try {
                    var rv = CarSignalParser().parseAsRSUStatus(p0!!.value as ByteArray)
                    Log.d(TAG, "callbackRV3Status="+rv.toString())
                    sendBlocking(rv)
                } catch (e:Exception) {
                    Log.e(TAG, e.toString())
                }
            }
            override fun onErrorEvent(p0: Int, p1: Int) {
                Log.d(TAG, "callbackRV3Status=error")
            }
        }
        manager.registerListener(callback, ID_PROPERTY_V2X_RV3, 0.0f)
        awaitClose { manager.unregisterListener(callback) }
    }

    fun callbackRV4Status() = callbackFlow<RSUStatus> {
        while (!_car.isConnected) {}
        var manager = _car.getCarManager(android.car.Car.PROPERTY_SERVICE) as CarPropertyManager
        var callback = object : CarPropertyManager.CarPropertyEventListener {
            override fun onChangeEvent(p0: CarPropertyValue<*>?) {
                try {
                    var rv = CarSignalParser().parseAsRSUStatus(p0!!.value as ByteArray)
                    Log.d(TAG, "callbackRV4Status="+rv.toString())
                    sendBlocking(rv)
                } catch (e:Exception) {
                    Log.e(TAG, e.toString())
                }
            }
            override fun onErrorEvent(p0: Int, p1: Int) {
                Log.d(TAG, "callbackRV4Status=error")
            }
        }
        manager.registerListener(callback, ID_PROPERTY_V2X_RV4, 0.0f)
        awaitClose { manager.unregisterListener(callback) }
    }

}