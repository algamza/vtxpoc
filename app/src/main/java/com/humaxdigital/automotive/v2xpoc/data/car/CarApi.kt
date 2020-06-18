package com.humaxdigital.automotive.v2xpoc.data.car


import android.car.hardware.CarSensorEvent
import android.extension.car.CarEx
import android.extension.car.CarSensorManagerEx
import android.extension.car.value.CarSensorEventEx
import android.util.Log
import kotlinx.coroutines.channels.*
import kotlinx.coroutines.flow.callbackFlow

class CarApi(car: CarEx) {
    private val _car = car

    private var v2xState : Int = 0
    private var vehicleSpeed : Int = 0
    private var gear : Int = 0
    private var turnLightState : Int = 0
    private var speedLimit : Int = 0
    private var warningType : Int = 0
    private var warningDirection : Int = 0
    private var warningSeverity : Int = 0
    private var warningRange : Int = 0
    private var warningPushed : Int = 0
    private var warningIconId : Int = 0
    private var warningAudioId : Int = 0
    private var warningTextId : Int = 0
    private var informType : Int = 0
    private var informDirection : Int = 0
    private var informSeverity : Int = 0
    private var informRange : Int = 0
    private var informPushed : Int = 0
    private var informIconId : Int = 0
    private var informAudioId : Int = 0
    private var informTextId : Int = 0
    private var configurationHMI : Int = 0
    private var hazardState : Int = 0
    private var breakState : Int = 0
    private var bluetoothState : Int = 0

    // VehicleStatus
    fun getV2XStatus() = callbackFlow<Int> { send(v2xState) }
    fun getVehicleSpeed() = callbackFlow<Int> { send(vehicleSpeed) }
    fun getGearState() = callbackFlow<Int> {
        send(gear)
        while(!_car.isConnected) {Log.d("TEST", "is not connected")}
        var sensor = _car.getCarManager(android.car.Car.SENSOR_SERVICE) as CarSensorManagerEx
        sensor.registerListener(object : CarSensorManagerEx.OnSensorChangedListenerEx{
            override fun onSensorChanged(p0: CarSensorEventEx?) {
            }
            override fun onSensorChanged(p0: CarSensorEvent?) {
                if ( p0 == null ) return
                when(p0.sensorType) {
                    CarSensorManagerEx.SENSOR_TYPE_GEAR -> offer(p0.getGearData(null).gear)
                }
            }
        }, CarSensorManagerEx.SENSOR_TYPE_GEAR, CarSensorManagerEx.SENSOR_RATE_NORMAL)
        var _gear = sensor.getLatestSensorEvent(
            CarSensorManagerEx.SENSOR_TYPE_GEAR).getGearData(null).gear
        send(_gear)
        awaitClose {}
    }
    fun getTurnLightState() = callbackFlow<Int> { send(turnLightState) }
    fun getSpeedLimit() = callbackFlow<Int> { send(speedLimit) }

    // Warning
    fun getWarningType() = callbackFlow<Int> { send(warningType) }
    fun getWarningDirection() = callbackFlow<Int> { send(warningDirection) }
    fun getWarningSeverity() = callbackFlow<Int> { send(warningSeverity) }
    fun getWarningRange() = callbackFlow<Int> { send(warningRange) }
    fun getWarningPushed() = callbackFlow<Int> { send(warningPushed) }
    fun getWarningIconId() = callbackFlow<Int> { send(warningIconId) }
    fun getWarningAudioId() = callbackFlow<Int> { send(warningAudioId) }
    fun getWarningTextId() = callbackFlow<Int> { send(warningTextId) }

    // Inform
    fun getInformType() = callbackFlow<Int> { send(informType) }
    fun getInformDirection() = callbackFlow<Int> { send(informDirection) }
    fun getInformSeverity() = callbackFlow<Int> { send(informSeverity) }
    fun getInformRange() = callbackFlow<Int> { send(informRange) }
    fun getInformPushed() = callbackFlow<Int> { send(informPushed) }
    fun getInformIconId() = callbackFlow<Int> { send(informIconId) }
    fun getInformAudioId() = callbackFlow<Int> { send(informAudioId) }
    fun getInformTextId() = callbackFlow<Int> { send(informTextId) }

    // ETC
    fun getConfigurationHMI() = callbackFlow<Int> { send(configurationHMI) }
    fun getHazardState() = callbackFlow<Int> { send(hazardState) }
    fun getBreakState() = callbackFlow<Int> { send(breakState) }
    fun getBluetoothState() = callbackFlow<Int> { send(bluetoothState) }
}