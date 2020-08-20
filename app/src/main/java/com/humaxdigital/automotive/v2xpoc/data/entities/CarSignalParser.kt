package com.humaxdigital.automotive.v2xpoc.data.entities

import android.security.keystore.ArrayUtils
import android.util.Log

class CarSignalParser constructor() {
    private val TAG = this.javaClass.name

    fun parseAsWarnInform(data: ByteArray) : WarnInform {
        var type = data[0].toUByte().toInt()
        var direction = data[1].toUByte().toInt()
        var severity = data[2].toUByte().toInt()
        var range = data[3].toUByte().toInt()
        var icon_id = data[4].toUByte().toInt()
        var audio_id = data[5].toUByte().toInt()
        var text_id = data[6].toUByte().toInt()
        var pushed = data[7].toUByte().toInt()
        return WarnInform(type, direction, severity, range, icon_id, audio_id, text_id, pushed)
            .also { Log.d(TAG, it.toString()) }
    }

    fun parseAsSPaT(data: ByteArray) : SPaT {
        var sl_phase = data[0].toUByte().toInt()
        var sl_end = data[1].toUByte().toInt()
        var lt_phase = data[2].toUByte().toInt()
        var lt_end = data[3].toUByte().toInt()
        var rt_phase = data[4].toUByte().toInt()
        var rt_end = data[5].toUByte().toInt()
        var ut_phase = data[6].toUByte().toInt()
        var ut_end = data[7].toUByte().toInt()
        return SPaT(sl_phase, sl_end, lt_phase, lt_end, rt_phase, rt_end, ut_phase, ut_end)
            .also { Log.d(TAG, it.toString()) }
    }

    fun parseAsVehicleStatus(data: ByteArray) : VehicleStatus {
        var status = data[0].toUByte().toInt()
        var speed = data[1].toUByte().toInt()
        var gear = data[2].toUByte().toInt()
        var light = data[3].toUByte().toInt()
        var optimal = data[4].toUByte().toInt()
        var limit = data[5].toUByte().toInt()
        var radius = ByteStream().bytesToUInt(byteArrayOf(data[6], data[7])).toInt()
        return VehicleStatus(status, speed, gear, light, optimal, limit, radius)
            .also { Log.d(TAG, it.toString()) }
    }

    fun parseAsHVPos(data: ByteArray) : HVPos {
        var lat = ByteStream().bytesToFloat(byteArrayOf(data[0], data[1], data[2], data[3]))
        var lon = ByteStream().bytesToFloat(byteArrayOf(data[4], data[5], data[6], data[7]))
        return HVPos(lat, lon).also { Log.d(TAG, it.toString()) }
    }

    fun parseAsHVMotion(data: ByteArray) : HVMotion {
        var alt =  ByteStream().bytesToFloat(byteArrayOf(data[0], data[1]))
        var speed = ByteStream().bytesToFloat(byteArrayOf(data[2], data[3]))
        var vehicle_heading = ByteStream().bytesToFloat(byteArrayOf(data[4], data[5]))
        var motion_heading = ByteStream().bytesToFloat(byteArrayOf(data[6], data[7]))
        return HVMotion(alt, speed, vehicle_heading, motion_heading)
            .also { Log.d(TAG, it.toString()) }
    }

    fun parseAsRSUStatus(data: ByteArray) : RSUStatus {
        var lon = ByteStream().bytesToFloat(byteArrayOf(data[0], data[1], data[2]))
        var lat = ByteStream().bytesToFloat(byteArrayOf(data[3], data[4], data[5]))
        var text = data[6].toUByte().toInt()
        var icon = data[7].toUByte().toInt()
        return RSUStatus(lon, lat, text, icon).also { Log.d(TAG, it.toString()) }
    }

    fun parseAsTrackingObject(data: ByteArray) : TrackingObj {
        var to1_type = ByteStream().byteToUInt(data[0], 0, 2).toInt()
        var to1_status = ByteStream().byteToUInt(data[0], 2, 2).toInt()
        var to1_lat = ByteStream().byteToInt(data[0], 4, 4)
        var to1_lon = data[1].toInt()

        var to2_type = ByteStream()
            .byteToUInt(data[2], 0, 2).toInt()
        var to2_status = ByteStream()
            .byteToUInt(data[2], 2, 2).toInt()
        var to2_lat = ByteStream()
            .byteToInt(data[2], 4, 4)
        var to2_lon = data[3].toInt()

        var to3_type = ByteStream()
            .byteToUInt(data[4], 0, 2).toInt()
        var to3_status = ByteStream()
            .byteToUInt(data[4], 2, 2).toInt()
        var to3_lat = ByteStream()
            .byteToInt(data[4], 4, 4)
        var to3_lon = data[5].toInt()

        var to4_type = ByteStream()
            .byteToUInt(data[6], 0, 2).toInt()
        var to4_status = ByteStream()
            .byteToUInt(data[6], 2, 2).toInt()
        var to4_lat = ByteStream()
            .byteToInt(data[6], 4, 4)
        var to4_lon = data[7].toInt()

        return TrackingObj(to1_type, to1_status, to1_lat, to1_lon,
            to2_type, to2_status, to2_lat, to2_lon,
            to3_type, to3_status, to3_lat, to3_lon,
            to4_type, to4_status, to4_lat, to4_lon).also { Log.d(TAG, it.toString()) }
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
        return Ext(sig1, sig2, sig3, sig4, sig5, sig6, sig7, sig8).also { Log.d(TAG, it.toString()) }
    }
}