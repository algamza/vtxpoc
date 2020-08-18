package com.humaxdigital.automotive.v2xpoc.data.entities

data class WarnInform (
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
    var rt_end: Int,
    var ut_phase: Int,
    var ut_end: Int
)

data class VehicleStatus(
    var status: Int,
    var speed: Int,
    var gear: Int,
    var light: Int,
    var optimal_speed: Int,
    var speed_limit: Int,
    var radius_curve: Int
)

data class HVPos(
    var lat: Float,
    var lon: Float
)

data class HVMotion(
    var alt: Float,
    var vehicle_speed: Float,
    var vehicle_heading: Float,
    var motion_heading: Float
)

data class RSUStatus(
    var lon_offset: Float,
    var lat_offset: Float,
    var text_id: Int,
    var icon_id: Int
)

data class TrackingObj(
    var to1_type: Int,
    var to1_status: Int,
    var to1_lat_dist: Int,
    var to1_long_dist: Int,
    var to2_type: Int,
    var to2_status: Int,
    var to2_lat_dist: Int,
    var to2_long_dist: Int,
    var to3_type: Int,
    var to3_status: Int,
    var to3_lat_dist: Int,
    var to3_long_dist: Int,
    var to4_type: Int,
    var to4_status: Int,
    var to4_lat_dist: Int,
    var to4_long_dist: Int
)

data class Ext(
    var sig1: Int,
    var sig2: Int,
    var sig3: Int,
    var sig4: Int,
    var sig5: Int,
    var sig6: Int,
    var sig7: Int,
    var sig8: Int
)

