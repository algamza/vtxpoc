package com.humaxdigital.automotive.v2xpoc.data.car

import android.car.hardware.CarSensorEvent
import android.extension.car.CarEx
import android.extension.car.CarSensorManagerEx
import android.extension.car.value.CarSensorEventEx
import com.humaxdigital.automotive.v2xpoc.data.entities.Inform
import com.humaxdigital.automotive.v2xpoc.data.entities.SPaT
import com.humaxdigital.automotive.v2xpoc.data.entities.VehicleStatus
import com.humaxdigital.automotive.v2xpoc.data.entities.Warning
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch

class CarApi(car: CarEx) {
    private val _car = car

    private var warning = Warning(0, 0, 0, 0, 0, 0, 0, 0)
    private var inform = Inform(0, 0, 0, 0, 0, 0, 0, 0)
    private var spat = SPaT(0, 0, 0, 0, 0, 0)
    private var vehicle = VehicleStatus(0, 0, 0, 0, 0, 0)

    // TEST
    private var _test_warning_count = 0
    private var _test_inform_count = 0
    private var _test_spat_count = 0
    private var _test_vehicle_count = 0

    init {
        GlobalScope.launch {
            while(!_car.isConnected) {}
            var sensor = _car.getCarManager(android.car.Car.SENSOR_SERVICE) as CarSensorManagerEx
            sensor.registerListener(object : CarSensorManagerEx.OnSensorChangedListenerEx{
                override fun onSensorChanged(p0: CarSensorEventEx?) {
                }
                override fun onSensorChanged(p0: CarSensorEvent?) {
                    if ( p0 == null ) return
                    when(p0.sensorType) {
                        CarSensorManagerEx.SENSOR_TYPE_GEAR -> {

                        }
                    }
                }
            }, CarSensorManagerEx.SENSOR_TYPE_GEAR, CarSensorManagerEx.SENSOR_RATE_NORMAL)
        }
    }

    fun getWarning() = callbackFlow<Warning> {
        send(warning)
        while(!_car.isConnected) {}

        var sensor = _car.getCarManager(android.car.Car.SENSOR_SERVICE) as CarSensorManagerEx
        sensor.registerListener(object : CarSensorManagerEx.OnSensorChangedListenerEx{
            override fun onSensorChanged(p0: CarSensorEventEx?) {
            }
            override fun onSensorChanged(p0: CarSensorEvent?) {
                if ( p0 == null ) return
                when(p0.sensorType) {
                    CarSensorManagerEx.SENSOR_TYPE_GEAR -> {
                        when(p0.getGearData(null).gear) {
                            0->{
                                _testUpdateWarning()
                                offer(warning)
                            }
                        }
                    }
                }
            }
        }, CarSensorManagerEx.SENSOR_TYPE_GEAR, CarSensorManagerEx.SENSOR_RATE_NORMAL)
        /*
        var _gear = sensor.getLatestSensorEvent(
            CarSensorManagerEx.SENSOR_TYPE_GEAR).getGearData(null).gear
        warning.type = _gear
        */
        awaitClose {}
    }

    fun getInform() = callbackFlow<Inform> {
        send(inform)
        while(!_car.isConnected) {}
        var sensor = _car.getCarManager(android.car.Car.SENSOR_SERVICE) as CarSensorManagerEx
        sensor.registerListener(object : CarSensorManagerEx.OnSensorChangedListenerEx{
            override fun onSensorChanged(p0: CarSensorEventEx?) {
            }
            override fun onSensorChanged(p0: CarSensorEvent?) {
                if ( p0 == null ) return
                when(p0.sensorType) {
                    CarSensorManagerEx.SENSOR_TYPE_GEAR -> {
                        when(p0.getGearData(null).gear) {
                            1->{
                                _testUpdateInform()
                                offer(inform)
                            }
                        }
                    }
                }
            }
        }, CarSensorManagerEx.SENSOR_TYPE_GEAR, CarSensorManagerEx.SENSOR_RATE_NORMAL)

        awaitClose {}
    }

    fun getVehicle() = callbackFlow<VehicleStatus> {
        send(vehicle)
        while(!_car.isConnected) {}
        var sensor = _car.getCarManager(android.car.Car.SENSOR_SERVICE) as CarSensorManagerEx
        sensor.registerListener(object : CarSensorManagerEx.OnSensorChangedListenerEx{
            override fun onSensorChanged(p0: CarSensorEventEx?) {
            }
            override fun onSensorChanged(p0: CarSensorEvent?) {
                if ( p0 == null ) return
                when(p0.sensorType) {
                    CarSensorManagerEx.SENSOR_TYPE_GEAR -> {
                        when(p0.getGearData(null).gear) {
                            2->{
                                _testUpdateVehicle()
                                offer(vehicle)
                            }
                        }
                    }
                }
            }
        }, CarSensorManagerEx.SENSOR_TYPE_GEAR, CarSensorManagerEx.SENSOR_RATE_NORMAL)

        awaitClose {}
    }

    fun getSPaT() = callbackFlow<SPaT> {
        send(spat)

        while(!_car.isConnected) {}

        var sensor = _car.getCarManager(android.car.Car.SENSOR_SERVICE) as CarSensorManagerEx
        sensor.registerListener(object : CarSensorManagerEx.OnSensorChangedListenerEx{
            override fun onSensorChanged(p0: CarSensorEventEx?) {
            }
            override fun onSensorChanged(p0: CarSensorEvent?) {
                if ( p0 == null ) return
                when(p0.sensorType) {
                    CarSensorManagerEx.SENSOR_TYPE_GEAR -> {
                        when(p0.getGearData(null).gear) {
                            3->{
                                _testUpdateSpat()
                                offer(spat)
                            }
                        }
                    }
                }
            }
        }, CarSensorManagerEx.SENSOR_TYPE_GEAR, CarSensorManagerEx.SENSOR_RATE_NORMAL)

        awaitClose {}
    }

    private fun _testUpdateWarning() {
        _test_warning_count++
        if ( _test_warning_count > 4 ) _test_warning_count = 0
        when(_test_warning_count) {
            0->_testUpdataWarning(2, 4, 1, 10, 2, 2, 0, 1)
            1->_testUpdataWarning(3, 3, 2, 20, 3, 3, 3, 2)
            2->_testUpdataWarning(4, 2, 1, 40, 4, 4, 4, 3)
            3->_testUpdataWarning(5, 1, 0, 20, 5, 5, 5, 1)
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