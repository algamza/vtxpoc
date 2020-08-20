package com.humaxdigital.automotive.v2xpoc.data.entities

import android.util.Log
import java.nio.ByteBuffer
import java.nio.ByteOrder

class ByteStream constructor() {
    private val TAG = this.javaClass.name
    fun bytesToUInt(bytes: ByteArray) : UInt {
        var result = 0u
        var shift = 0
        for ( byte in bytes ) {
            result = result or (byte.toUByte().toUInt() shl shift)
            shift += 8
        }

        return result
    }

    fun bytesToFloat(bytes: ByteArray) : Float {
        //Log.d(TAG, ByteBuffer.wrap(bytes).float.toString())
        return 0.0f//ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN).float
    }

    fun bytesToInt(bytes: ByteArray) : Int {
        return 0
    }

    fun byteToUInt(byte: Byte, start: Int, length: Int) : UByte {
        return 0u
    }

    fun byteToInt(byte: Byte, start: Int, length: Int) : Int {
        return 0
    }
}