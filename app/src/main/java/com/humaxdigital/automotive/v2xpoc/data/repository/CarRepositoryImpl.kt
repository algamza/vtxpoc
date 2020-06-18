package com.humaxdigital.automotive.v2xpoc.data.repository

import android.extension.car.CarSensorManagerEx
import android.util.Log
import com.humaxdigital.automotive.v2xpoc.data.car.CarApi
import com.humaxdigital.automotive.v2xpoc.domain.entities.*
import com.humaxdigital.automotive.v2xpoc.domain.repositories.CarRepository
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CarRepositoryImpl constructor(private val api : CarApi) : CarRepository {
    override fun getV2XStatus(): Flow<V2XSTATE> {
        TODO("Not yet implemented")
    }

    override fun getVehicleSpeed(): Flow<Int> {
        TODO("Not yet implemented")
    }

    override fun getGearState(): Flow<GEAR> {
        api.getGearState()
        return api.getGearState().map {
            when (it) {
                0 -> GEAR.D
                1 -> GEAR.R
                2 -> GEAR.N
                3 -> GEAR.D
                else -> GEAR.P
            }
        }
    }

    override fun getTurnLightState(): Flow<TURNLIGHT> {
        TODO("Not yet implemented")
    }

    override fun getSpeedLimit(): Flow<Int> {
        TODO("Not yet implemented")
    }

    override fun callbackV2XStatus(): ReceiveChannel<V2XSTATE> {
        TODO("Not yet implemented")
    }

    override fun callbackVehicleSpeed(): ReceiveChannel<Int> {
        TODO("Not yet implemented")
    }

    override fun callbackGearState(): ReceiveChannel<GEAR> {
        TODO("Not yet implemented")
    }

    override fun callbackTurnLightState(): ReceiveChannel<TURNLIGHT> {
        TODO("Not yet implemented")
    }

    override fun callbackSpeedLimit(): ReceiveChannel<Int> {
        TODO("Not yet implemented")
    }

    override fun getWarningType(): Flow<TYPE> {
        TODO("Not yet implemented")
    }

    override fun getWarningDirection(): Flow<DIRECTION> {
        TODO("Not yet implemented")
    }

    override fun getWarningSeverity(): Flow<SEVERITY> {
        TODO("Not yet implemented")
    }

    override fun getWarningRange(): Flow<Int> {
        TODO("Not yet implemented")
    }

    override fun getWarningPushed(): Flow<PUSHED> {
        TODO("Not yet implemented")
    }

    override fun getWarningIconId(): Flow<Int> {
        TODO("Not yet implemented")
    }

    override fun getWarningAudioId(): Flow<Int> {
        TODO("Not yet implemented")
    }

    override fun getWarningTextId(): Flow<Int> {
        TODO("Not yet implemented")
    }

    override fun getInformType(): Flow<TYPE> {
        TODO("Not yet implemented")
    }

    override fun getInformDirection(): Flow<DIRECTION> {
        TODO("Not yet implemented")
    }

    override fun getInformSeverity(): Flow<SEVERITY> {
        TODO("Not yet implemented")
    }

    override fun getInformRange(): Flow<Int> {
        TODO("Not yet implemented")
    }

    override fun getInformPushed(): Flow<PUSHED> {
        TODO("Not yet implemented")
    }

    override fun getInformIconId(): Flow<Int> {
        TODO("Not yet implemented")
    }

    override fun getInformAudioId(): Flow<Int> {
        TODO("Not yet implemented")
    }

    override fun getInformTextId(): Flow<Int> {
        TODO("Not yet implemented")
    }

    override fun getConfigurationHMI(): Flow<CONFIGURATIONHMI> {
        TODO("Not yet implemented")
    }

    override fun getHazardState(): Flow<HAZARD> {
        TODO("Not yet implemented")
    }

    override fun getBreakState(): Flow<BREAK> {
        TODO("Not yet implemented")
    }

    override fun getBluetoothState(): Flow<BLUETOOTH> {
        TODO("Not yet implemented")
    }
}