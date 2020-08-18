package com.humaxdigital.automotive.v2xpoc.data.entities

import android.util.Log
import com.android.internal.util.BitwiseInputStream

class CarSignalParser constructor() {
    private val TAG = this.javaClass.name

    fun parseAsWarnInform(data: ByteArray) : WarnInform {
        var type = data[0].toUInt().toInt()
        var direction = data[1].toUInt().toInt()
        var severity = data[2].toUInt().toInt()
        var range = data[3].toUInt().toInt()
        var icon_id = data[4].toUInt().toInt()
        var audio_id = data[5].toUInt().toInt()
        var text_id = data[6].toUInt().toInt()
        var pushed = data[7].toUInt().toInt()
        var warninfo = WarnInform(type, direction, severity, range, icon_id, audio_id, text_id, pushed)
        Log.d(TAG, warninfo.toString())
        return warninfo
    }

    fun parseAsSPaT(data: ByteArray) : SPaT {
        var sl_phase = data[0].toUInt().toInt()
        var sl_end = data[1].toUInt().toInt()
        var lt_phase = data[2].toUInt().toInt()
        var lt_end = data[3].toUInt().toInt()
        var rt_phase = data[4].toUInt().toInt()
        var rt_end = data[5].toUInt().toInt()
        var ut_phase = data[6].toUInt().toInt()
        var ut_end = data[7].toUInt().toInt()
        var spat = SPaT(sl_phase, sl_end, lt_phase, lt_end, rt_phase, rt_end, ut_phase, ut_end)
        Log.d(TAG, spat.toString())
        return spat
    }

    fun parseAsVehicleStatus(data: ByteArray) : VehicleStatus {
        var status = data[0].toUInt().toInt()
        var speed = data[1].toUInt().toInt()
        var gear = data[2].toUInt().toInt()
        var light = data[3].toUInt().toInt()
        var optimal = data[4].toUInt().toInt()
        var limit = data[5].toUInt().toInt()
        var radius = (data[6].toUInt().shl(8) or data[7].toUInt()).toInt()
        var vs = VehicleStatus(status, speed, gear, light, optimal, limit, radius)
        Log.d(TAG, vs.toString())
        return vs
    }

    fun parseAsHVPos(data: ByteArray) : HVPos {
        var lat = (data[0].toUInt().shl(24) or data[1].toUInt().shl(16)
                or data[2].toUInt().shl(8) or data[3].toUInt()).toFloat()
        var lon = (data[4].toUInt().shl(24) or data[5].toUInt().shl(16)
                or data[6].toUInt().shl(8) or data[7].toUInt()).toFloat()
        var hvpos = HVPos(lat, lon)
        Log.d(TAG, hvpos.toString())
        return hvpos
    }

    fun parseAsHVMotion(data: ByteArray) : HVMotion {
        var alt = (data[0].toUInt().shl(8) or data[1].toUInt()).toFloat()
        var speed = (data[2].toUInt().shl(8) or data[3].toUInt()).toFloat()
        var vehicle_heading = (data[4].toUInt().shl(8) or data[5].toUInt()).toFloat()
        var motion_heading = (data[6].toUInt().shl(8) or data[7].toUInt()).toFloat()
        var hvmotion = HVMotion(alt, speed, vehicle_heading, motion_heading)
        Log.d(TAG, hvmotion.toString())
        return hvmotion
    }

    fun parseAsRSUStatus(data: ByteArray) : RSUStatus {

        var lon = (data[0].toUInt().shl(16) or data[1].toUInt().shl(8) or data[2].toUInt()).toFloat()
        var lat = (data[3].toUInt().shl(16) or data[4].toUInt().shl(8) or data[5].toUInt()).toFloat()
        var text = data[6].toUInt().toInt()
        var icon = data[7].toUInt().toInt()
        var rsu = RSUStatus(lon, lat, text, icon)
        Log.d(TAG, rsu.toString())
        return rsu
    }

    fun parseAsTrackingObject(data: ByteArray) : TrackingObj {
        val stream =  BitwiseInputStream(data)

        stream.read(2).toUInt() as Int

        var to1_type = data[0].toUByte().toInt().shr(6) and 0x03
        var to1_status = data[0].toUByte().toInt().shr(4) and 0x03
        var to1_lat = data[0].toInt() and 0x0F
        var to1_lon = data[1].toInt()

        var to2_type = data[2].toUByte().toInt().shr(6) and 0x03
        var to2_status = data[2].toUByte().toInt().shr(4) and 0x03
        var to2_lat = data[2].toInt()
        var to2_lon = data[3].toInt()

        var to3_type = data[4].toUByte().toInt().shr(6) and 0x03
        var to3_status = data[4].toUByte().toInt().shr(4) and 0x03
        var to3_lat = data[4].toInt()
        var to3_lon = data[5].toInt()

        var to4_type = data[6].toUByte().toInt().shr(6) and 0x03
        var to4_status = data[6].toUByte().toInt().shr(4) and 0x03
        var to4_lat = data[6].toInt() and 0x0F
        var to4_lon = data[7].toInt()

        Log.d(TAG, "data[0]="+data[0].toString())
        Log.d(TAG, "data[1]="+data[1].toString())
        Log.d(TAG, "data[2]="+data[2].toString())
        Log.d(TAG, "data[3]="+data[3].toString())
        Log.d(TAG, "data[4]="+data[4].toString())
        Log.d(TAG, "data[5]="+data[5].toString())
        Log.d(TAG, "data[6]="+data[6].toString())
        Log.d(TAG, "data[7]="+data[7].toString())


        var to = TrackingObj(to1_type, to1_status, to1_lat, to1_lon,
            to2_type, to2_status, to2_lat, to2_lon,
            to3_type, to3_status, to3_lat, to3_lon,
            to4_type, to4_status, to4_lat, to4_lon)

        Log.d(TAG, to.toString())
        return to
    }

    fun parseAsExt(data: ByteArray) : Ext {
        var sig1 = data[0].toUInt().toInt()
        var sig2 = data[1].toUInt().toInt()
        var sig3 = data[2].toUInt().toInt()
        var sig4 = data[3].toUInt().toInt()
        var sig5 = data[4].toUInt().toInt()
        var sig6 = data[5].toUInt().toInt()
        var sig7 = data[6].toUInt().toInt()
        var sig8 = data[7].toUInt().toInt()
        var ext = Ext(sig1, sig2, sig3, sig4, sig5, sig6, sig7, sig8)
        return ext
    }
}