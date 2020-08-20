package com.humaxdigital.automotive.v2xpoc.domain.entities

enum class TYPE { NONE, HB, FCW, ICW, LTA, BSW_LCW, DNPW, EBW,
    AVW, CLW, HLW, SLW, RLVW, VRUCW, GLOSA, IVS, TJW, EVW }
enum class SEVERITY { NONE, L0, L1, L2, L3, L4, L5, L6, L7, L8, L9 }
enum class PUSHED { NONE, ADD, UPDATE, DELETE }
enum class GEAR { NONE, N, P, D, R, RESERVED1, RESERVED2, RESERVED3, RESERVED4 }
enum class SIGNAL { UNKNOWN, RED, YELLOW, GREEN }
enum class TRACKYPE { CAR, EMERGENCY, PEDASTRAIN }
enum class TRACKSTATUS { OFF, ON, WARN }

data class EMOTICON (
    var type1: Boolean,
    var type2: Boolean,
    var type3: Boolean,
    var type4: Boolean,
    var type5: Boolean,
    var type6: Boolean,
    var leader: Boolean,
    var follower: Boolean
)
data class WOWCMD (
    var emoticon_ready: Boolean,
    var emoticon_send: Boolean,
    var emoticon_recv: Boolean,
    var emoticon_stop: Boolean,
    var location_ready: Boolean,
    var location_send: Boolean,
    var location_recv: Boolean,
    var location_stop: Boolean
)

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
    var optimal_speed: Int,
    var speed_limit: Int,
    var radius_curve: Int
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
    var rt_end: Int,
    var ut_phase: SIGNAL,
    var ut_end: Int
)

data class V2XHVPos(
    var lat: Float,
    var lon: Float
)

data class V2XHVMotion(
    var alt: Float,
    var vehicle_speed: Float,
    var vehicle_heading: Float,
    var motion_heading: Float
)

data class V2XRSUStatus(
    var lat_offset: Float,
    var lon_offset: Float,
    var text_id: Int,
    var icon_id: Int
)

data class V2XTrackingObj(
    var to1_type: TRACKYPE,
    var to1_status: TRACKSTATUS,
    var to1_lat_dist: Int,
    var to1_long_dist: Int,
    var to2_type: TRACKYPE,
    var to2_status: TRACKSTATUS,
    var to2_lat_dist: Int,
    var to2_long_dist: Int,
    var to3_type: TRACKYPE,
    var to3_status: TRACKSTATUS,
    var to3_lat_dist: Int,
    var to3_long_dist: Int,
    var to4_type: TRACKYPE,
    var to4_status: TRACKSTATUS,
    var to4_lat_dist: Int,
    var to4_long_dist: Int
)

data class V2XWow(
    var cmd: WOWCMD,
    var emotion: EMOTICON,
    var direction: DIRECTION,
    var range: Int,
    var counter: Int
)