package com.humaxdigital.automotive.v2xpoc.domain.entities

enum class TYPE {NONE, NO_WARN, INTERSECTION_COLLISION, LEFT_TURN_ASSIST, EMERGENCY_BRAKE,
    ABNORMAL_VEHICLE, HAZARD_LOCATION, RED_LIGHT_VIOLATION, IN_VEHICLE_SIGNAGE, EMERGENCY_VEHICLE}
enum class SEVERITY {NONE, L1, L2, L3}
enum class PUSHED {NONE, ADD, UPDATE, DELETE}
enum class V2XSTATE {NONE, GPS, V2V, V2I, APP}

enum class GEAR {NONE, D, N, P, R}
enum class BREAKLIGHT {NONE, ON, OFF}
enum class TURNLIGHT {NONE, LEFT, RIGHT, OFF, HAZARD}
enum class LANE {NONE, LANE1, LANE2, LANE3}
enum class ONOFF { ON, OFF }

data class Direction(
    var left : ONOFF,
    var right: ONOFF,
    var forward: ONOFF,
    var backward: ONOFF
)

data class V2XWarning(
    var type : TYPE,
    var direction: Direction,
    var severity: SEVERITY,
    var range: Int,
    var icon_id: Int,
    var audio_id: Int,
    var text_id: Int,
    var pushed: PUSHED
)

data class V2XInform(
    var type : TYPE,
    var direction: Direction,
    var severity: SEVERITY,
    var range: Int,
    var icon_id: Int,
    var audio_id: Int,
    var text_id: Int,
    var pushed: PUSHED
)

data class V2XSPaT(
    var sl_phase: Int,
    var sl_end: Int,
    var lt_phase: Int,
    var lt_end: Int,
    var rt_phase: Int,
    var rt_end: Int
)

data class V2XVehicleStatus(
    var v2xstatus: V2XSTATE,
    var speed: Int,
    var gear: GEAR,
    var light: TURNLIGHT,
    var lane: LANE,
    var speed_limit: Int
)