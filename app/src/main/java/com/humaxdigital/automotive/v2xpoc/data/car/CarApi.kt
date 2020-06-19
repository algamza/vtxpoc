package com.humaxdigital.automotive.v2xpoc.data.car


import android.car.hardware.CarSensorEvent
import android.extension.car.CarEx
import android.extension.car.CarSensorManagerEx
import android.extension.car.value.CarSensorEventEx
import com.humaxdigital.automotive.v2xpoc.data.entities.Inform
import com.humaxdigital.automotive.v2xpoc.data.entities.SPaT
import com.humaxdigital.automotive.v2xpoc.data.entities.VehicleStatus
import com.humaxdigital.automotive.v2xpoc.data.entities.Warning
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow

class CarApi(car: CarEx) {
    private val _car = car

    private lateinit var warning : Warning
    private lateinit var inform: Inform
    private lateinit var spat: SPaT
    private lateinit var vehicle: VehicleStatus

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
                    CarSensorManagerEx.SENSOR_TYPE_GEAR -> offer(Warning(p0.getGearData(null).gear, 0, 0, 0, 0, 0, 0, 0))
                }
            }
        }, CarSensorManagerEx.SENSOR_TYPE_GEAR, CarSensorManagerEx.SENSOR_RATE_NORMAL)
        var _gear = sensor.getLatestSensorEvent(
            CarSensorManagerEx.SENSOR_TYPE_GEAR).getGearData(null).gear
        warning = Warning(_gear, 0, 0, 0, 0, 0, 0, 0)
        send(warning)
        awaitClose {}
    }


    fun getInform() = callbackFlow<Inform> {
        send(inform)
        awaitClose {}
    }

    fun getVehicle() = callbackFlow<VehicleStatus> {
        send(vehicle)
        awaitClose {}
    }

    fun getSPaT() = callbackFlow<SPaT> {
        send(spat)
        awaitClose {}
    }
}