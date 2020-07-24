package com.humaxdigital.automotive.v2xpoc.data.entities

import com.humaxdigital.automotive.v2xpoc.domain.entities.*

/*
VAL_ 1876 RT_SignalPhase 3 "green=3" 2 "yellow=2" 1 "red=1" 0 "unknown=0" ;
VAL_ 1876 LT_SignalPhase 3 "green=3" 2 "yellow=2" 1 "red=1" 0 "unknown=0" ;
VAL_ 1876 SL_SignalPhase 3 "green=3" 2 "yellow=2" 1 "red=1" 0 "unknown=0" ;
VAL_ 1875 LaneType 41 "right_curved_lv9" 40 "right_curved_lv8" 39 "right_curved_lv7" 38 "right_curved_lv6" 37 "right_curved_lv5" 36 "right_curved_lv4" 35 "right_curved_lv3" 34 "right_curved_lv2" 33 "right_curved_lv1" 32 "right_curved" 25 "left_curved-lv9" 24 "left_curved-lv8" 23 "left_curved-lv7" 22 "left_curved-lv6" 21 "left_curved-lv5" 20 "left_curved-lv4" 19 "left_curved-lv3" 18 "left_curved-lv2" 17 "left_curved-lv1" 16 "left_curved=0x10" 1 "straight=1" 0 "unknown=0" ;
VAL_ 1875 LightStatus 16 "abs=0x10" 8 "hazard=0x08" 4 "turn_right=0x04" 2 "turn_left=0x02" 1 "brake=0x01" 0 "off=0" ;
VAL_ 1875 GearStatus 7 "reserved4=7" 6 "reserved3=6" 5 "reserved2=5" 4 "reserved1=4" 3 "reversegears=3" 2 "farwardgears=2" 1 "park=1" 0 "neutral=0" ;
VAL_ 1875 V2XStatus 8 "APP" 4 "V2I" 2 "V2V" 1 "GPS" 0 "ALL_OFF" ;
VAL_ 1874 Inform_Severity 9 "L9" 8 "L8" 7 "L7" 6 "L6" 5 "L5" 4 "L4" 3 "L3" 2 "L2" 1 "L1" 0 "L0" ;
VAL_ 1874 Inform_Direction 8 "BACKWARD" 4 "FORWARD" 2 "RIGHT" 1 "LEFT" 0 "Unknown" ;
VAL_ 1874 Inform_Type 16 "EVW" 15 "TJW" 14 "IVS" 13 "GLOSA" 12 "VRUCW" 11 "RLVW" 10 "SLW" 9 "HLW" 8 "CLW" 7 "AVW" 6 "EBW" 5 "DNPW" 4 "BSW/LCW" 3 "LTA" 2 "ICW" 1 "FCW" 0 "HeartBeat message to HMI" ;
VAL_ 1873 Warn_Severity 9 "L9" 8 "L8" 7 "L7" 6 "L6" 5 "L5" 4 "L4" 3 "L3" 2 "L2" 1 "L1" 0 "L0" ;
VAL_ 1873 Warn_Direction 8 "BACKWARD" 4 "FORWARD" 2 "RIGHT" 1 "LEFT" 0 "Unknown" ;
VAL_ 1873 Warn_Type 16 "EVW" 15 "TJW" 14 "IVS" 13 "GLOSA" 12 "VRUCW" 11 "RLVW" 10 "SLW" 9 "HLW" 8 "CLW" 7 "AVW" 6 "EBW" 5 "DNPW" 4 "BSW/LCW" 3 "LTA" 2 "ICW" 1 "FCW" 0 "HeartBeat message to HMI" ;
*/

class CarSignalMapper constructor() {
    fun mapToEntity(warning: WarnInform) : V2XWarnInform {
        return V2XWarnInform(getType(warning.type), getDirection(warning.direction), getSeverity(warning.severity)
            , warning.range, warning.icon_id, warning.audio_id, warning.text_id, getPushed(warning.pushed))
    }
    fun mapToEntity(vehicleStatus: VehicleStatus) : V2XVehicleStatus {
        return V2XVehicleStatus(getV2XState(vehicleStatus.status), vehicleStatus.speed, getGear(vehicleStatus.gear)
            , getLight(vehicleStatus.light), getLane(vehicleStatus.lane), vehicleStatus.speed_limit)
    }
    fun mapToEntity(sPaT: SPaT) : V2XSPaT {
        return V2XSPaT(getSignal(sPaT.sl_phase), sPaT.sl_end, getSignal(sPaT.lt_phase),
            sPaT.lt_end, getSignal(sPaT.rt_phase), sPaT.rt_end)
    }
    fun mapToEntity(hvpos: HVPos) : V2XHVPos {
        return V2XHVPos(hvpos.lon, hvpos.lat)
    }
    fun mapToEntity(hvmotion: HVMotion) : V2XHVMotion {
        return V2XHVMotion(hvmotion.motion_heading, hvmotion.vehicle_heading, hvmotion.vehicle_speed, hvmotion.alt)
    }
    fun mapToEntity(rv: RSUStatus) : V2XRSUStatus {
        return V2XRSUStatus(rv.text_id, rv.icon_id, rv.lon_offset, rv.lat_offset)
    }

    private fun getSignal(signal:Int) : SIGNAL {
        var _signal = SIGNAL.UNKNOWN
        when(signal) {
            0->_signal=SIGNAL.UNKNOWN
            1->_signal=SIGNAL.RED
            2->_signal=SIGNAL.YELLOW
            3->_signal=SIGNAL.GREEN
        }
        return _signal
    }

    private fun getLane(lane:Int) : LANE {
        var _lane = LANE.UNKNOWN
        when(lane) {
            0->_lane=LANE.UNKNOWN
            1->_lane=LANE.STRAIGHT
            16->_lane=LANE.LCURVED
            17->_lane=LANE.LCURVED_L1
            18->_lane=LANE.LCURVED_L2
            19->_lane=LANE.LCURVED_L3
            20->_lane=LANE.LCURVED_L4
            21->_lane=LANE.LCURVED_L5
            22->_lane=LANE.LCURVED_L6
            23->_lane=LANE.LCURVED_L7
            24->_lane=LANE.LCURVED_L8
            25->_lane=LANE.LCURVED_L9
            32->_lane=LANE.RCURVED
            33->_lane=LANE.RCURVED_L1
            34->_lane=LANE.RCURVED_L2
            35->_lane=LANE.RCURVED_L3
            36->_lane=LANE.RCURVED_L4
            37->_lane=LANE.RCURVED_L5
            38->_lane=LANE.RCURVED_L6
            39->_lane=LANE.RCURVED_L7
            40->_lane=LANE.RCURVED_L8
            41->_lane=LANE.RCURVED_L9
        }
        return _lane
    }

    private fun getLight(light:Int) : LIGHT {
        var _mask : Int = 1
        var _light = LIGHT(false, false, false, false, false)
        if ( light and _mask.shl(0) != 0 ) _light.brake = true
        if ( light and _mask.shl(1) != 0 ) _light.left = true
        if ( light and _mask.shl(2) != 0 ) _light.right = true
        if ( light and _mask.shl(3) != 0 ) _light.hazard = true
        if ( light and _mask.shl(4) != 0 ) _light.abs = true
        return _light
    }

    private fun getGear(gear:Int) : GEAR {
        var _gear = GEAR.NONE
        when(gear) {
            0->_gear=GEAR.N
            1->_gear=GEAR.P
            2->_gear=GEAR.D
            3->_gear=GEAR.R
            4->_gear=GEAR.RESERVED1
            5->_gear=GEAR.RESERVED2
            6->_gear=GEAR.RESERVED3
            7->_gear=GEAR.RESERVED4
        }
        return _gear
    }

    private fun getV2XState(state:Int) : STATUS {
        var _mask : Int = 1
        var _state = STATUS(false, false, false, false)
        if ( state and _mask.shl(0) != 0 ) _state.gps = true
        if ( state and _mask.shl(1) != 0 ) _state.v2v = true
        if ( state and _mask.shl(2) != 0 ) _state.v2i = true
        if ( state and _mask.shl(3) != 0 ) _state.app = true
        return _state
    }

    private fun getPushed(pushed: Int) : PUSHED {
        var _pushed = PUSHED.NONE
        when(pushed) {
            1->_pushed=PUSHED.ADD
            2->_pushed=PUSHED.UPDATE
            3->_pushed=PUSHED.DELETE
        }
        return _pushed
    }

    private fun getSeverity(severity:Int) : SEVERITY {
        var _serverity = SEVERITY.NONE
        when(severity) {
            0->_serverity=SEVERITY.L0
            1->_serverity=SEVERITY.L1
            2->_serverity=SEVERITY.L2
            3->_serverity=SEVERITY.L3
            4->_serverity=SEVERITY.L4
            5->_serverity=SEVERITY.L5
            6->_serverity=SEVERITY.L6
            7->_serverity=SEVERITY.L7
            8->_serverity=SEVERITY.L8
            9->_serverity=SEVERITY.L9
        }
        return _serverity
    }

    private fun getType(type:Int) : TYPE {
        var _type = TYPE.NONE
        when(type) {
            0->_type=TYPE.HB
            1->_type=TYPE.FCW
            2->_type=TYPE.ICW
            3->_type=TYPE.LTA
            4->_type=TYPE.BSW_LCW
            5->_type=TYPE.DNPW
            6->_type=TYPE.EBW
            7->_type=TYPE.AVW
            8->_type=TYPE.CLW
            9->_type=TYPE.HLW
            10->_type=TYPE.SLW
            11->_type=TYPE.RLVW
            12->_type=TYPE.VRUCW
            13->_type=TYPE.GLOSA
            14->_type=TYPE.IVS
            15->_type=TYPE.TJW
            16->_type=TYPE.EVW
        }
        return _type
    }

    private fun getDirection(direction:Int) : DIRECTION {
        var _mask : Int = 1
        var _direction = DIRECTION(false, false, false, false)
        if ( direction and _mask.shl(0) != 0 ) _direction.left = true
        if ( direction and _mask.shl(1) != 0 ) _direction.right = true
        if ( direction and _mask.shl(2) != 0 ) _direction.forward = true
        if ( direction and _mask.shl(3) != 0 ) _direction.backward = true
        return _direction
    }
}