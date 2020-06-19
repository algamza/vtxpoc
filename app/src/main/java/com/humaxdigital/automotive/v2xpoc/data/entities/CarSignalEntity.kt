package com.humaxdigital.automotive.v2xpoc.data.entities

data class Warning(
    var type : Int,
    var direction: Int,
    var severity: Int,
    var range: Int,
    var icon_id: Int,
    var audio_id: Int,
    var text_id: Int,
    var pushed: Int
)

data class Inform(
    var type : Int,
    var direction: Int,
    var severity: Int,
    var range: Int,
    var icon_id: Int,
    var audio_id: Int,
    var text_id: Int,
    var pushed: Int
)

data class SPaT(
    var sl_phase: Int,
    var sl_end: Int,
    var lt_phase: Int,
    var lt_end: Int,
    var rt_phase: Int,
    var rt_end: Int
)

data class VehicleStatus(
    var status: Int,
    var speed: Int,
    var gear: Int,
    var light: Int,
    var lane: Int,
    var speed_limit: Int
)

