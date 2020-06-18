package com.humaxdigital.automotive.v2xpoc.domain.entities

enum class TYPE {NO_WARN, INTERSECTION_COLLISION, LEFT_TURN_ASSIST, EMERGENCY_BRAKE, ABNORMAL_VEHICLE, HAZARD_LOCATION, RED_LIGHT_VIOLATION, IN_VEHICLE_SIGNAGE, EMERGENCY_VEHICLE}
enum class DIRECTION {UNKNOWN, LEFT, RIGHT, FORWARD, BACKWARD}
enum class SEVERITY {L1, L2, L3}
enum class PUSHED {NONE, ADD, UPDATE, DELETE}
enum class V2XSTATE {S1, S2}

enum class HAZARD {ON, OFF}
enum class GEAR {D, N, P, R}
enum class BREAK {ON, OFF}
enum class BLUETOOTH {ON, OFF}
enum class CONFIGURATIONHMI {ON, OFF}
enum class TURNLIGHT {LEFT, RIGHT, OFF}