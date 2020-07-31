package com.humaxdigital.automotive.v2xpoc.data.entities

import java.nio.ByteBuffer

class CarSignalParser constructor() {
    fun parseAsWarnInform(data: ByteArray) : WarnInform {
        var type = data[0]
        var direction = data[1]
        var severity = data[2]
        var range = data[3]
        var icon_id = data[4]
        var audio_id = data[5]
        var text_id = data[6]
        var pushed = data[7]
        return WarnInform(type.toInt(), direction.toInt(), severity.toInt(), range.toInt(),
            icon_id.toInt(), audio_id.toInt(), text_id.toInt(), pushed.toInt())
    }

    fun parseAsSPaT(data: ByteArray) : SPaT {
        var sl_phase = data[0]
        var sl_end = data[1]
        var lt_phase = data[2]
        var lt_end = data[3]
        var rt_phase = data[4]
        var rt_end = data[5]
        return SPaT(sl_phase.toInt(), sl_end.toInt(), lt_phase.toInt(), lt_end.toInt(),
            rt_phase.toInt(), rt_end.toInt())
    }

    fun parseAsVehicleStatus(data: ByteArray) : VehicleStatus {
        var status = data[0]
        var speed = data[1]
        var gear = data[2]
        var light = data[3]
        var lane = data[4]
        var limit = data[5]
        return VehicleStatus(status.toInt(), speed.toInt(), gear.toInt(), light.toInt(),
            lane.toInt(), limit.toInt())
    }

    fun parseAsHVPos(data: ByteArray) : HVPos {
        var lat = (data[0].toInt().shl(24)) or data[1].toInt().shl(16) or
                data[2].toInt().shl(8) or data[3].toInt()
        var lon = (data[4].toInt().shl(24)) or data[5].toInt().shl(16) or
                data[6].toInt().shl(8) or data[7].toInt()
        return HVPos(lon.toLong(), lat.toLong())
    }

    fun parseAsHVMotion(data: ByteArray) : HVMotion {
        var alt = data[0].toInt().shl(8) or data[1].toInt()
        var speed = data[2].toInt().shl(8) or data[3].toInt()
        var vehicle_heading = data[4].toInt().shl(8) or data[5].toInt()
        var motion_heading = data[6].toInt().shl(8) or data[7].toInt()
        return HVMotion(motion_heading, vehicle_heading, speed, alt.toLong())
    }

    fun parseAsRSUStatus(data: ByteArray) : RSUStatus {
        var lat_offset = data[0].toInt().shl(16) or
                data[1].toInt().shl(8) or data[2].toInt()
        var icon_id = data[3]
        var lon_offset = data[4].toInt().shl(16) or
                data[5].toInt().shl(8) or data[6].toInt()
        var text_id = data[7]
        return RSUStatus(text_id.toInt(), icon_id.toInt(), lon_offset.toLong(), lat_offset.toLong() )
    }
}