package com.humaxdigital.automotive.v2xpoc.domain.repositories

import com.humaxdigital.automotive.v2xpoc.domain.entities.*
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.flow.Flow


interface CarRepository {

    // VehicleStatus
    fun getV2XStatus() : Flow<V2XSTATE>
    fun getVehicleSpeed() : Flow<Int>
    fun getGearState() : Flow<GEAR>
    fun getTurnLightState() : Flow<TURNLIGHT>
    fun getSpeedLimit() : Flow<Int>

    // Warning
    fun getWarningType() : Flow<TYPE>
    fun getWarningDirection() : Flow<DIRECTION>
    fun getWarningSeverity() : Flow<SEVERITY>
    fun getWarningRange() : Flow<Int>
    fun getWarningPushed() : Flow<PUSHED>
    fun getWarningIconId() : Flow<Int>
    fun getWarningAudioId() : Flow<Int>
    fun getWarningTextId() : Flow<Int>

    // Inform
    fun getInformType() : Flow<TYPE>
    fun getInformDirection() : Flow<DIRECTION>
    fun getInformSeverity() : Flow<SEVERITY>
    fun getInformRange() : Flow<Int>
    fun getInformPushed() : Flow<PUSHED>
    fun getInformIconId() : Flow<Int>
    fun getInformAudioId() : Flow<Int>
    fun getInformTextId() : Flow<Int>

    // ETC
    fun getConfigurationHMI() : Flow<CONFIGURATIONHMI>
    fun getHazardState() : Flow<HAZARD>
    fun getBreakState() : Flow<BREAK>
    fun getBluetoothState() : Flow<BLUETOOTH>


    fun callbackV2XStatus() : ReceiveChannel<V2XSTATE>
    fun callbackVehicleSpeed() : ReceiveChannel<Int>
    fun callbackGearState() : ReceiveChannel<GEAR>
    fun callbackTurnLightState() : ReceiveChannel<TURNLIGHT>
    fun callbackSpeedLimit() : ReceiveChannel<Int>
}