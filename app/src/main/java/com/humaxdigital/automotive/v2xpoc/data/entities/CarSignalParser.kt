package com.humaxdigital.automotive.v2xpoc.data.entities

import kotlin.experimental.and

/*
        var eventId = (data[0] and 0xff.toByte()).toInt().shl(24) or
                (data[1] and 0xff.toByte()).toInt().shl(16) or
                (data[2] and 0xff.toByte()).toInt().shl(0) or
                (data[3] and 0xff.toByte()).toInt()
 */
class CarSignalParser constructor() {
    fun parseAsWarnInform(data: ByteArray) : WarnInform {
        return WarnInform(data[0].toInt(), data[1].toInt(), data[2].toInt(), data[3].toInt(),
            data[4].toInt(), data[5].toInt(), data[6].toInt(), data[7].toInt())
    }

    fun parseAsSPaT(data: ByteArray) : SPaT {
        return SPaT(data[0].toInt(), data[1].toInt(), data[2].toInt(),
            data[3].toInt(), data[4].toInt(), data[5].toInt())
    }

    fun parseAsVehicleStatus(data: ByteArray) : VehicleStatus {
        return VehicleStatus(data[0].toInt(), data[1].toInt(), data[2].toInt(),
            data[3].toInt(), data[4].toInt(), data[5].toInt())
    }

    fun parseAsHVPos(data: ByteArray) : HVPos {
        var lat = (data[0].toInt().shl(24)) or
                data[1].toInt().shl(16) or
                data[2].toInt().shl(8) or
                data[3].toInt()
        var lon = (data[4].toInt().shl(24)) or
                data[5].toInt().shl(16) or
                data[6].toInt().shl(8) or
                data[7].toInt()
        return HVPos(lon.toDouble(), lat.toDouble())
    }

    fun parseAsHVMotion(data: ByteArray) : HVMotion {
        return HVMotion(0, 0, 0, 0.0 )
    }

    fun parseAsRSUStatus(data: ByteArray) : RSUStatus {
        return RSUStatus(0, 0, 0.0, 0.0 )
    }
}