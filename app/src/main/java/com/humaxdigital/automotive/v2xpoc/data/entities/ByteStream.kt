package com.humaxdigital.automotive.v2xpoc.data.entities

import android.util.Log

class ByteStream constructor() {
    private val TAG = this.javaClass.name
    fun bytesToUInt(bytes: ByteArray) : UInt {
        var result = 0
        var shift = 0
        for ( byte in bytes ) {
            Log.d(TAG, "byte="+byte.toString())
            result = result or (byte.toInt() shl shift)
            shift += 8
        }

        return result.toUInt()
    }

    fun bytesToFloat(bytes: ByteArray) : Float {
        return 0.0f
    }

    fun bytesToInt(bytes: ByteArray) : Int {
        return 0
    }

    fun byteToUByte(byte: Byte, start: Int, length: Int) : UByte {
        return 0u
    }

    fun byteToInt(byte: Byte, start: Int, length: Int) : Int {
        return 0
    }
}