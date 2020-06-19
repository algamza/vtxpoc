package com.humaxdigital.automotive.v2xpoc.data.entities

import com.humaxdigital.automotive.v2xpoc.domain.entities.*

class CarSignalMapper constructor() {
    fun mapToEntity(warning: Warning) : V2XWarning {
        return V2XWarning(getType(warning.type), getDirection(warning.direction), getSeverity(warning.severity)
            , warning.range, warning.icon_id, warning.audio_id, warning.text_id, getPushed(warning.pushed))
    }
    fun mapToEntity(inform: Inform) : V2XInform {
        return V2XInform(getType(inform.type), getDirection(inform.direction), getSeverity(inform.severity)
            , inform.range, inform.icon_id, inform.audio_id, inform.text_id, getPushed(inform.pushed))
    }
    fun mapToEntity(vehicleStatus: VehicleStatus) : V2XVehicleStatus {
        return V2XVehicleStatus(getV2XState(vehicleStatus.status), vehicleStatus.speed, getGear(vehicleStatus.gear)
            , getLight(vehicleStatus.light), getLane(vehicleStatus.lane), vehicleStatus.speed_limit)
    }
    fun mapToEntity(sPaT: SPaT) : V2XSPaT {
        return V2XSPaT(sPaT.sl_phase, sPaT.sl_end, sPaT.lt_phase, sPaT.lt_end, sPaT.rt_phase, sPaT.rt_end)
    }

    private fun getLane(lane:Int) : LANE {
        var _lane = LANE.NONE
        // TODO : tuned for can signal
        when(lane) {
            1->_lane=LANE.LANE1
            2->_lane=LANE.LANE2
            3->_lane=LANE.LANE3
        }
        return _lane
    }

    private fun getLight(light:Int) : TURNLIGHT {
        var _light = TURNLIGHT.NONE
        // TODO : tuned for can signal
        when(light) {
            1->_light=TURNLIGHT.LEFT
            2->_light=TURNLIGHT.RIGHT
            3->_light=TURNLIGHT.OFF
            4->_light=TURNLIGHT.HAZARD
        }
        return _light
    }

    private fun getGear(gear:Int) : GEAR {
        var _gear = GEAR.NONE
        // TODO : tuned for can signal
        when(gear) {
            1->_gear=GEAR.N
            2->_gear=GEAR.D
            3->_gear=GEAR.P
            4->_gear=GEAR.R
        }
        return _gear
    }

    private fun getV2XState(state:Int) : V2XSTATE {
        var _state = V2XSTATE.NONE
        // TODO : tuned for can signal
        when(state) {
            1->_state=V2XSTATE.APP
            2->_state=V2XSTATE.GPS
            3->_state=V2XSTATE.V2I
            4->_state=V2XSTATE.V2V
        }
        return _state
    }

    private fun getPushed(pushed: Int) : PUSHED {
        var _pushed = PUSHED.NONE
        // TODO : tuned for can signal
        when(pushed) {
            1->_pushed=PUSHED.ADD
            2->_pushed=PUSHED.UPDATE
            3->_pushed=PUSHED.DELETE
        }
        return _pushed
    }

    private fun getSeverity(severity:Int) : SEVERITY {
        var _serverity = SEVERITY.NONE
        // TODO : tuned for can signal
        when(severity) {
            1->_serverity=SEVERITY.L1
            2->_serverity=SEVERITY.L2
            3->_serverity=SEVERITY.L3
        }
        return _serverity
    }

    private fun getType(type:Int) : TYPE {
        var _type = TYPE.NONE
        // TODO : tuned for can signal
        when(type) {
            0->_type=TYPE.NO_WARN
            2->_type=TYPE.INTERSECTION_COLLISION
            3->_type=TYPE.LEFT_TURN_ASSIST
            6->_type=TYPE.EMERGENCY_BRAKE
            7->_type=TYPE.ABNORMAL_VEHICLE
            9->_type=TYPE.HAZARD_LOCATION
            11->_type=TYPE.RED_LIGHT_VIOLATION
            14->_type=TYPE.IN_VEHICLE_SIGNAGE
            16->_type=TYPE.EMERGENCY_VEHICLE
        }
        return _type
    }

    private fun getDirection(direction:Int) : Direction {
        var left = ONOFF.OFF
        var right = ONOFF.OFF
        var forward = ONOFF.OFF
        var backward = ONOFF.OFF
        // TODO : tuned for can signal
        when(direction) {
            1->left=ONOFF.ON
            2->right=ONOFF.ON
            3->{
                left=ONOFF.ON
                right=ONOFF.ON
            }
            4->forward=ONOFF.ON
            5->{
                forward=ONOFF.ON
                left=ONOFF.ON
            }
            6->{
                forward=ONOFF.ON
                right=ONOFF.ON
            }
            7->{
                forward=ONOFF.ON
                left=ONOFF.ON
                right=ONOFF.ON
            }
            8->backward=ONOFF.ON
            9->{
                backward=ONOFF.ON
                left=ONOFF.ON
            }
            10->{
                backward=ONOFF.ON
                right=ONOFF.ON
            }
            11->{
                backward=ONOFF.ON
                left=ONOFF.ON
                right=ONOFF.ON
            }
            12->{
                backward=ONOFF.ON
                forward=ONOFF.ON
            }
            13->{
                backward=ONOFF.ON
                forward=ONOFF.ON
                left=ONOFF.ON
            }
            14->{
                backward=ONOFF.ON
                forward=ONOFF.ON
                right=ONOFF.ON
            }
            15->{
                backward=ONOFF.ON
                forward=ONOFF.ON
                left=ONOFF.ON
                right=ONOFF.ON
            }
        }
        return Direction(left, right, forward, backward)
    }
}