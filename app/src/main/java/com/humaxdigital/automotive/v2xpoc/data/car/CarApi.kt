package com.humaxdigital.automotive.v2xpoc.data.car

import android.car.VehicleAreaType
import android.car.hardware.CarPropertyValue
import android.car.hardware.property.CarPropertyManager
import android.extension.car.CarEx
import com.humaxdigital.automotive.v2xpoc.data.entities.*
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.sendBlocking
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow


class CarApi(car: CarEx) {
    private val ID_PROPERTY_V2X_WARNING : Int = 0x11400400 // SENSOR_TYPE_GEAR
    private val ID_PROPERTY_V2X_INFORM : Int = 0x0001
    private val ID_PROPERTY_V2X_SPAT : Int = 0x0002
    private val ID_PROPERTY_V2X_STATUS : Int = 0x0003
    private val ID_PROPERTY_V2X_HVPOS : Int = 0x0004
    private val ID_PROPERTY_V2X_HVMOTION : Int = 0x0005
    private val ID_PROPERTY_V2X_RV1 : Int = 0x0006
    private val ID_PROPERTY_V2X_RV2 : Int = 0x0007
    private val ID_PROPERTY_V2X_RV3 : Int = 0x0008
    private val ID_PROPERTY_V2X_RV4 : Int = 0x0009

    private val _car = car

    private var warning = WarnInform(0, 0, 0, 0, 0, 0, 0, 0)
    private var inform = WarnInform(0, 0, 0, 0, 0, 0, 0, 0)
    private var spat = SPaT(0, 0, 0, 0, 0, 0)
    private var vehicle = VehicleStatus(0, 0, 0, 0, 0, 0)
    private var hvpos = HVPos(0.0, 0.0)
    private var hvmotion = HVMotion(0, 0, 0, 0.0)
    private var rv1 = RSUStatus(0, 0, 0.0, 0.0)
    private var rv2 = RSUStatus(0, 0, 0.0, 0.0)
    private var rv3 = RSUStatus(0, 0, 0.0, 0.0)
    private var rv4 = RSUStatus(0, 0, 0.0, 0.0)

    init { }

    fun getWarning() = flow<WarnInform> {
        while (!_car.isConnected) {}
        var manager = _car.getCarManager(android.car.Car.PROPERTY_SERVICE) as CarPropertyManager
        var value = manager.getProperty<ByteArray>(
            ID_PROPERTY_V2X_WARNING,
            VehicleAreaType.VEHICLE_AREA_TYPE_GLOBAL)
        // todo
    }

    fun getInform() = flow<WarnInform> {
        while (!_car.isConnected) {}
        var manager = _car.getCarManager(android.car.Car.PROPERTY_SERVICE) as CarPropertyManager
        var value = manager.getProperty<ByteArray>(
            ID_PROPERTY_V2X_INFORM,
            VehicleAreaType.VEHICLE_AREA_TYPE_GLOBAL)
        // todo
    }

    fun getVehicleStatus() = flow<VehicleStatus> {
        var manager = _car.getCarManager(android.car.Car.PROPERTY_SERVICE) as CarPropertyManager
        while (!_car.isConnected) {}
        var value = manager.getProperty<ByteArray>(
            ID_PROPERTY_V2X_STATUS,
            VehicleAreaType.VEHICLE_AREA_TYPE_GLOBAL)
        // todo
    }

    fun getSPaT() = flow<SPaT> {
        while (!_car.isConnected) {}
        var manager = _car.getCarManager(android.car.Car.PROPERTY_SERVICE) as CarPropertyManager
        var value = manager.getProperty<ByteArray>(
            ID_PROPERTY_V2X_SPAT,
            VehicleAreaType.VEHICLE_AREA_TYPE_GLOBAL)
        // todo
    }

    fun getHVPos() = flow<HVPos> {
        while (!_car.isConnected) {}
        var manager = _car.getCarManager(android.car.Car.PROPERTY_SERVICE) as CarPropertyManager
        var value = manager.getProperty<ByteArray>(
            ID_PROPERTY_V2X_HVPOS,
            VehicleAreaType.VEHICLE_AREA_TYPE_GLOBAL)
        // todo
    }

    fun getHVMotion() = flow<HVMotion> {
        while (!_car.isConnected) {}
        var manager = _car.getCarManager(android.car.Car.PROPERTY_SERVICE) as CarPropertyManager
        var value = manager.getProperty<ByteArray>(
            ID_PROPERTY_V2X_HVMOTION,
            VehicleAreaType.VEHICLE_AREA_TYPE_GLOBAL)
        // todo
    }

    fun getRV1Status() = flow<RSUStatus> {
        while (!_car.isConnected) {}
        var manager = _car.getCarManager(android.car.Car.PROPERTY_SERVICE) as CarPropertyManager
        var value = manager.getProperty<ByteArray>(
            ID_PROPERTY_V2X_RV1,
            VehicleAreaType.VEHICLE_AREA_TYPE_GLOBAL)
        // todo
    }

    fun getRV2Status() = flow<RSUStatus> {
        while (!_car.isConnected) {}
        var manager = _car.getCarManager(android.car.Car.PROPERTY_SERVICE) as CarPropertyManager
        var value = manager.getProperty<ByteArray>(
            ID_PROPERTY_V2X_RV2,
            VehicleAreaType.VEHICLE_AREA_TYPE_GLOBAL)
        // todo
    }

    fun getRV3Status() = flow<RSUStatus> {
        while (!_car.isConnected) {}
        var manager = _car.getCarManager(android.car.Car.PROPERTY_SERVICE) as CarPropertyManager
        var value = manager.getProperty<ByteArray>(
            ID_PROPERTY_V2X_RV3,
            VehicleAreaType.VEHICLE_AREA_TYPE_GLOBAL)
        // todo
    }

    fun getRV4Status() = flow<RSUStatus> {
        while (!_car.isConnected) {}
        var manager = _car.getCarManager(android.car.Car.PROPERTY_SERVICE) as CarPropertyManager
        var value = manager.getProperty<ByteArray>(
            ID_PROPERTY_V2X_RV4,
            VehicleAreaType.VEHICLE_AREA_TYPE_GLOBAL)
        // todo
    }


    fun callbackWarning() = callbackFlow<WarnInform> {
        while (!_car.isConnected) {}
        var manager = _car.getCarManager(android.car.Car.PROPERTY_SERVICE) as CarPropertyManager
        var callback = object : CarPropertyManager.CarPropertyEventListener {
            override fun onChangeEvent(p0: CarPropertyValue<*>?) {
                if ( p0!!.propertyId == ID_PROPERTY_V2X_WARNING ) {
                    // test
                    _testUpdateWarning()

                    sendBlocking(warning)
                }
            }
            override fun onErrorEvent(p0: Int, p1: Int) {
                // todo
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
                sendBlocking(inform)
            }
            override fun onErrorEvent(p0: Int, p1: Int) {
                // todo
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
                sendBlocking(vehicle)
            }
            override fun onErrorEvent(p0: Int, p1: Int) {
                // todo
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
                sendBlocking(spat)
            }
            override fun onErrorEvent(p0: Int, p1: Int) {
                // todo
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
                sendBlocking(hvpos)
            }
            override fun onErrorEvent(p0: Int, p1: Int) {
                // todo
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
                sendBlocking(hvmotion)
            }
            override fun onErrorEvent(p0: Int, p1: Int) {
                // todo
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
                sendBlocking(rv1)
            }
            override fun onErrorEvent(p0: Int, p1: Int) {
                // todo
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
                sendBlocking(rv2)
            }
            override fun onErrorEvent(p0: Int, p1: Int) {
                // todo
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
                sendBlocking(rv3)
            }
            override fun onErrorEvent(p0: Int, p1: Int) {
                // todo
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
                sendBlocking(rv4)
            }
            override fun onErrorEvent(p0: Int, p1: Int) {
                // todo
            }
        }
        manager.registerListener(callback, ID_PROPERTY_V2X_RV4, 0.0f)
        awaitClose { manager.unregisterListener(callback) }
    }


    // TEST
    private var _test_warning_count = 0
    private var _test_inform_count = 0
    private var _test_spat_count = 0
    private var _test_vehicle_count = 0

    private fun _testUpdateWarning() {
        _test_warning_count++
        if ( _test_warning_count > 8 ) _test_warning_count = 0
        when(_test_warning_count) {
            0->_testUpdataWarning(2, 4, 1, 10, 2, 2, 2, 1)
            1->_testUpdataWarning(3, 3, 2, 20, 3, 3, 3, 2)
            2->_testUpdataWarning(6, 2, 1, 40, 4, 6, 6, 3)
            3->_testUpdataWarning(7, 1, 0, 20, 5, 7, 7, 1)
            4->_testUpdataWarning(9,1, 0, 20, 9, 9, 9, 1)
            5->_testUpdataWarning(11, 1, 0, 20, 11, 11, 11, 1)
            6->_testUpdataWarning(14, 1, 0, 20, 14, 14, 14, 1)
            7->_testUpdataWarning(16, 1, 0, 20, 16, 16, 16, 1)
        }
    }

    private fun _testUpdataWarning(type:Int, direction:Int, severity:Int, range:Int, icon:Int, audio:Int, text:Int, pushed:Int) {
        warning.type = type
        warning.direction = direction
        warning.severity = severity
        warning.range = range
        warning.icon_id = icon
        warning.audio_id = audio
        warning.text_id = text
        warning.pushed = pushed
    }

    private fun _testUpdateInform() {
        _test_inform_count++
        if ( _test_inform_count > 5 ) _test_inform_count = 0
        when(_test_inform_count) {
            0->_testUpdataInform(2, 4, 1, 10, 2, 2, 0, 1)
            1->_testUpdataInform(3, 3, 2, 20, 3, 3, 3, 2)
            2->_testUpdataInform(4, 2, 1, 40, 4, 4, 4, 3)
            3->_testUpdataInform(5, 1, 0, 20, 5, 5, 5, 1)
            4->_testUpdataInform(1, 3, 1, 10, 2, 3, 4, 2)
        }
    }

    private fun _testUpdataInform(type:Int, direction:Int, severity:Int, range:Int, icon:Int, audio:Int, text:Int, pushed:Int) {
        inform.type = type
        inform.direction = direction
        inform.severity = severity
        inform.range = range
        inform.icon_id = icon
        inform.audio_id = audio
        inform.text_id = text
        inform.pushed = pushed
    }


    private fun _testUpdateSpat() {
        _test_spat_count++
        if ( _test_spat_count > 5 ) _test_spat_count = 0
        when(_test_spat_count) {
            0->_testUpdateSpat(0, 20, 1, 20, 1, 20)
            1->_testUpdateSpat(1, 20, 1, 20, 1, 20)
            2->_testUpdateSpat(0, 20, 0, 20, 1, 20)
            3->_testUpdateSpat(1, 20, 0, 20, 1, 20)
            4->_testUpdateSpat(0, 20, 1, 20, 0, 20)
        }
    }
    private fun _testUpdateSpat(slp:Int, sle: Int, ltp:Int, lte:Int, rtp:Int, rte:Int) {
        spat.sl_phase = slp
        spat.sl_end = sle
        spat.lt_phase = ltp
        spat.lt_end = lte
        spat.rt_phase = rtp
        spat.rt_end = rte
    }

    private fun _testUpdateVehicle() {
        _test_vehicle_count++
        if ( _test_vehicle_count > 8 ) _test_vehicle_count = 0
        when(_test_vehicle_count) {
            0->_testUpdateVehicle(2, 90, 1, 2, 3, 100)
            1->_testUpdateVehicle(1, 100, 0, 1, 1, 120)
            2->_testUpdateVehicle(3, 90, 1, 2, 3, 80)
            3->_testUpdateVehicle(4, 90, 2, 2, 1, 80)
            4->_testUpdateVehicle(5, 90, 1, 2, 3, 30)
            5->_testUpdateVehicle(2, 100, 2, 2, 3, 90)
            6->_testUpdateVehicle(3, 90, 3, 3, 1, 90)
            7->_testUpdateVehicle(2, 90, 1, 3, 0, 90)
            8->_testUpdateVehicle(2, 90, 2, 2, 3, 30)
        }
    }
    private fun _testUpdateVehicle(status:Int, spped:Int, gear:Int, light:Int, lane:Int, speedl:Int) {
        vehicle.status = status
        vehicle.speed = spped
        vehicle.gear = gear
        vehicle.light = light
        vehicle.lane = lane
        vehicle.speed_limit = speedl
    }
}