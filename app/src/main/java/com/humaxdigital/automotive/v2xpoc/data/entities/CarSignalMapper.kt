package com.humaxdigital.automotive.v2xpoc.data.entities

import com.humaxdigital.automotive.v2xpoc.domain.entities.*

class CarSignalMapper constructor() {
    fun mapToEntity(warning: WarnInform) : V2XWarnInform {
        return V2XWarnInform(getType(warning.type), getDirection(warning.direction), getSeverity(warning.severity)
            , warning.range, warning.icon_id, warning.audio_id, warning.text_id, getPushed(warning.pushed))
    }
    fun mapToEntity(vehicleStatus: VehicleStatus) : V2XVehicleStatus {
        return V2XVehicleStatus(getV2XState(vehicleStatus.status), vehicleStatus.speed, getGear(vehicleStatus.gear)
            , getLight(vehicleStatus.light), vehicleStatus.optimal_speed, vehicleStatus.speed_limit, vehicleStatus.radius_curve)
    }
    fun mapToEntity(sPaT: SPaT) : V2XSPaT {
        return V2XSPaT(getSignal(sPaT.sl_phase), sPaT.sl_end, getSignal(sPaT.lt_phase),
            sPaT.lt_end, getSignal(sPaT.rt_phase), sPaT.rt_end, getSignal(sPaT.ut_phase), sPaT.ut_end)
    }
    fun mapToEntity(hvpos: HVPos) : V2XHVPos {
        return V2XHVPos(hvpos.lat, hvpos.lon)
    }
    fun mapToEntity(hvmotion: HVMotion) : V2XHVMotion {
        return V2XHVMotion(hvmotion.alt, hvmotion.vehicle_speed, hvmotion.vehicle_heading, hvmotion.motion_heading)
    }
    fun mapToEntity(rv: RSUStatus) : V2XRSUStatus {
        return V2XRSUStatus(rv.lat_offset, rv.lon_offset, rv.text_id, rv.icon_id)
    }
    fun mapToEntity(obj: TrackingObj) : V2XTrackingObj {
        return V2XTrackingObj(getTrackingType(obj.to1_type), getTrackingStatus(obj.to1_status), obj.to1_lat_dist, obj.to1_long_dist,
            getTrackingType(obj.to2_type), getTrackingStatus(obj.to2_status), obj.to2_lat_dist, obj.to2_long_dist,
            getTrackingType(obj.to3_type), getTrackingStatus(obj.to3_status), obj.to3_lat_dist, obj.to3_long_dist,
            getTrackingType(obj.to4_type), getTrackingStatus(obj.to4_status), obj.to4_lat_dist, obj.to4_long_dist)
    }
    fun mapToEntity(ext: Ext) : V2XWow {
        return V2XWow(getWowCmd(ext.sig1), getEmoticon(ext.sig2), getDirection(ext.sig3), ext.sig4, ext.sig5)
    }

    fun mapToExt(wow: V2XWow) : Ext {
        return Ext(getExtSig1(wow.cmd), getExtSig2(wow.emotion), getExtSig3(wow.direction), wow.range, wow.counter, 0, 0, 0 )
    }

    private fun getEmoticon(emo: Int) : EMOTICON {
        var _cmd = EMOTICON(false, false, false,
            false, false, false, false, false)
        var _mask : Int = 1
        if ( emo and _mask.shl(0) != 0 ) _cmd.type1 = true
        if ( emo and _mask.shl(1) != 0 ) _cmd.type2 = true
        if ( emo and _mask.shl(2) != 0 ) _cmd.type3 = true
        if ( emo and _mask.shl(3) != 0 ) _cmd.type4 = true
        if ( emo and _mask.shl(4) != 0 ) _cmd.type5 = true
        if ( emo and _mask.shl(5) != 0 ) _cmd.type6 = true
        if ( emo and _mask.shl(6) != 0 ) _cmd.leader = true
        if ( emo and _mask.shl(7) != 0 ) _cmd.follower = true
        return _cmd
    }

    private fun getExtSig3(direction: DIRECTION) : Int {
        return 0
    }

    private fun getExtSig2(emotion: EMOTICON) : Int {
        return 0
    }

    private fun getExtSig1(cmd: WOWCMD) : Int {
        return 0
    }

    private fun getWowCmd(cmd: Int) : WOWCMD {
        var _cmd = WOWCMD(false, false, false,
            false, false, false, false, false)
        var _mask : Int = 1
        if ( cmd and _mask.shl(0) != 0 ) _cmd.emoticon_ready = true
        if ( cmd and _mask.shl(1) != 0 ) _cmd.emoticon_send = true
        if ( cmd and _mask.shl(2) != 0 ) _cmd.emoticon_stop = true
        if ( cmd and _mask.shl(3) != 0 ) _cmd.emoticon_recv = true
        if ( cmd and _mask.shl(4) != 0 ) _cmd.location_ready = true
        if ( cmd and _mask.shl(5) != 0 ) _cmd.location_send = true
        if ( cmd and _mask.shl(6) != 0 ) _cmd.location_recv = true
        if ( cmd and _mask.shl(7) != 0 ) _cmd.location_stop = true
        return _cmd
    }

    private fun getTrackingStatus(status:Int) : TRACKSTATUS {
        var _status = TRACKSTATUS.OFF
        when(status) {
            0 -> _status = TRACKSTATUS.OFF
            1 -> _status = TRACKSTATUS.ON
            2 -> _status = TRACKSTATUS.WARN
        }
        return _status
    }

    private fun getTrackingType(type:Int) : TRACKYPE {
        var _type = TRACKYPE.CAR
        when(type) {
            0 -> _type = TRACKYPE.CAR
            1 -> _type = TRACKYPE.EMERGENCY
            2 -> _type = TRACKYPE.PEDASTRAIN
        }
        return _type
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