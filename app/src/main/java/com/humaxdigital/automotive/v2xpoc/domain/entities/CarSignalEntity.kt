package com.humaxdigital.automotive.v2xpoc.domain.entities

enum class TYPE { NONE, HB, FCW, ICW, LTA, BSW_LCW, DNPW, EBW,
    AVW, CLW, HLW, SLW, RLVW, VRUCW, GLOSA, IVS, TJW, EVW }
enum class SEVERITY { NONE, L0, L1, L2, L3, L4, L5, L6, L7, L8, L9 }
enum class PUSHED { NONE, ADD, UPDATE, DELETE }
enum class GEAR { NONE, N, P, D, R, RESERVED1, RESERVED2, RESERVED3, RESERVED4 }
enum class LANE {
    UNKNOWN, STRAIGHT, LCURVED, LCURVED_L1, LCURVED_L2, LCURVED_L3, LCURVED_L4, LCURVED_L5,
    LCURVED_L6, LCURVED_L7, LCURVED_L8, LCURVED_L9, RCURVED, RCURVED_L1, RCURVED_L2, RCURVED_L3,
    RCURVED_L4, RCURVED_L5, RCURVED_L6, RCURVED_L7, RCURVED_L8, RCURVED_L9
}
enum class SIGNAL { UNKNOWN, RED, YELLOW, GREEN }
data class LIGHT(
    var brake: Boolean,
    var left: Boolean,
    var right: Boolean,
    var hazard: Boolean,
    var abs: Boolean
)
data class STATUS(
    var app : Boolean,
    var v2i : Boolean,
    var v2v : Boolean,
    var gps : Boolean
)
data class DIRECTION(
    var left : Boolean,
    var right: Boolean,
    var forward: Boolean,
    var backward: Boolean
)

data class V2XVehicleStatus(
    var v2xstatus: STATUS,
    var speed: Int,
    var gear: GEAR,
    var light: LIGHT,
    var lane: LANE,
    var speed_limit: Int
)

data class V2XWarnInform(
    var type : TYPE,
    var direction: DIRECTION,
    var severity: SEVERITY,
    var range: Int,
    var icon_id: Int,
    var audio_id: Int,
    var text_id: Int,
    var pushed: PUSHED
)

data class V2XSPaT(
    var sl_phase: SIGNAL,
    var sl_end: Int,
    var lt_phase: SIGNAL,
    var lt_end: Int,
    var rt_phase: SIGNAL,
    var rt_end: Int
)

data class V2XHVPos(
    var lon: Double,
    var lat: Double
)

data class V2XHVMotion(
    var motion_heading: Int,
    var vehicle_heading: Int,
    var vehicle_speed: Int,
    var alt: Double
)

data class V2XRSUStatus(
    var text_id: Int,
    var icon_id: Int,
    var lon_offset: Double,
    var lat_offset: Double
)
