package com.humaxdigital.automotive.v2xpoc.presentation.entities

import com.humaxdigital.automotive.v2xpoc.R
import com.humaxdigital.automotive.v2xpoc.domain.entities.*
import com.humaxdigital.automotive.v2xpoc.domain.entities.PUSHED

class ResourceMapper constructor() {
    fun mapToLightHazard(light: LIGHT) = when(light.hazard) {
        true -> R.drawable.v2x_ic_emergency
        false -> R.drawable.v2x_ic_emergency
    }
    fun mapToLightLeft(light: LIGHT) = when(light.left) {
        true -> R.drawable.v2x_ic_arrow_left_p
        false -> R.drawable.v2x_ic_arrow_left_n
    }
    fun mapToLightRight(light: LIGHT) = when(light.right) {
        true -> R.drawable.v2x_ic_arrow_right_p
        false -> R.drawable.v2x_ic_arrow_right_n
    }
    fun mapToLightBrake(light: LIGHT) = when(light.brake) {
        true -> R.drawable.v2x_img_car_basic_brake
        false -> R.drawable.v2x_img_car_basic
    }

    fun mapToGearP(gear: GEAR) = when(gear) {
        GEAR.P -> R.drawable.v2x_ic_gear_park_p
        else -> R.drawable.v2x_ic_gear_park_n
    }
    fun mapToGearD(gear: GEAR) = when(gear) {
        GEAR.D -> R.drawable.v2x_ic_gear_forward_p
        else -> R.drawable.v2x_ic_gear_forward_n
    }
    fun mapToGearN(gear: GEAR) = when(gear) {
        GEAR.N -> R.drawable.v2x_ic_gear_neutral_p
        else -> R.drawable.v2x_ic_gear_neutral_n
    }
    fun mapToGearR(gear: GEAR) = when(gear) {
        GEAR.R -> R.drawable.v2x_ic_gear_reverse_p
        else -> R.drawable.v2x_ic_gear_reverse_n
    }
    fun mapToV2XApp(status: STATUS) = when(status.app) {
        true -> R.drawable.v2x_ic_app_p
        false -> R.drawable.v2x_ic_app_n
    }
    fun mapToV2XGps(status: STATUS) = when(status.gps) {
        true -> R.drawable.v2x_ic_gps_p
        false -> R.drawable.v2x_ic_gps_n
    }
    fun mapToV2XV2V(status: STATUS) = when(status.v2v) {
        true -> R.drawable.v2x_ic_v2v_p
        false -> R.drawable.v2x_ic_v2v_n
    }
    fun mapToV2XV2I(status: STATUS) = when(status.v2i) {
        true -> R.drawable.v2x_ic_v2i_p
        false -> R.drawable.v2x_ic_v2i_n
    }
    fun mapToWarningText(index: Int, type: TYPE) = when(type) {
        TYPE.EBW -> {
            when(index) {
                1 -> R.string.warning_ebw_1
                else -> R.string.empty
            }
        }
        TYPE.EVW -> {
            when(index) {
                1 -> R.string.warning_evw_1
                2 -> R.string.warning_evw_2
                3 -> R.string.warning_evw_3
                else -> R.string.empty
            }
        }
        TYPE.ICW -> {
            when(index) {
                1 -> R.string.warning_icw_1
                else -> R.string.empty
            }
        }
        TYPE.LTA -> {
            when(index) {
                1 -> R.string.warning_lta_1
                else -> R.string.empty
            }
        }
        TYPE.FCW -> {
            when(index) {
                1 -> R.string.warning_fcw_1
                else -> R.string.empty
            }
        }
        TYPE.BSW_LCW -> {
            when(index) {
                1 -> R.string.warning_bsw_1
                else -> R.string.empty
            }
        }
        else -> R.string.empty
    }
    fun mapToWarningAudio(index: Int, type: TYPE) = when(type) {
        TYPE.EBW -> {
            when(index) {
                1 -> R.string.warning_ebw_1
                else -> R.string.empty
            }
        }
        TYPE.EVW -> {
            when(index) {
                1 -> R.string.warning_evw_1
                2 -> R.string.warning_evw_2
                3 -> R.string.warning_evw_3
                else -> R.string.empty
            }
        }
        TYPE.ICW -> {
            when(index) {
                1 -> R.string.warning_icw_1
                else -> R.string.empty
            }
        }
        TYPE.LTA -> {
            when(index) {
                1 -> R.string.warning_lta_1
                else -> R.string.empty
            }
        }
        TYPE.FCW -> {
            when(index) {
                1 -> R.string.warning_fcw_1
                else -> R.string.empty
            }
        }
        TYPE.BSW_LCW -> {
            when(index) {
                1 -> R.string.warning_bsw_1
                else -> R.string.empty
            }
        }
        else -> R.string.empty
    }
    fun mapToWarningIcon(index: Int, type: TYPE) = when(type) {
        TYPE.EBW -> {
            when(index) {
                1 -> R.drawable.v2x_ic_warning
                else -> 0
            }
        }
        TYPE.EVW -> {
            when(index) {
                1 -> R.drawable.v2x_img_pop_center
                2 -> R.drawable.v2x_img_pop_right
                3 -> R.drawable.v2x_img_pop_left
                else -> 0
            }
        }
        TYPE.BSW_LCW -> {
            when(index) {
                1 -> R.drawable.v2x_img_pop_bsw_l
                2 -> R.drawable.v2x_img_pop_bsw_r
                else -> 0
            }
        }
        else -> 0
    }
    fun mapToRange(range: Int, type: TYPE) = when(type) {
        TYPE.EBW -> {
            when(range) {
                in 0..69 -> R.drawable.v2x_img_level_warning_01
                in 70..210 -> R.drawable.v2x_img_level_warning_02
                in 210..255 -> R.drawable.v2x_img_level_warning_03
                else -> R.drawable.v2x_img_level_straight
            }
        }
        else -> R.drawable.v2x_img_level_straight
    }
    fun mapToDistance(range: Int) = when(range) {
        in 0..69 -> range.toString() + "m"
        in 70..97 -> (((range-70) * 5) + 70).toString() + "m"
        in 98..254 -> (((range-98) * 10) + 210).toString() + "m"
        else -> "Out of range"
    }
    fun mapToWarningType(type: TYPE) = when(type) {
        TYPE.NONE -> V2XTYPE.NONE
        TYPE.HB -> V2XTYPE.HB
        TYPE.FCW -> V2XTYPE.FCW
        TYPE.ICW -> V2XTYPE.ICW
        TYPE.LTA -> V2XTYPE.LTA
        TYPE.BSW_LCW -> V2XTYPE.BSW_LCW
        TYPE.DNPW -> V2XTYPE.DNPW
        TYPE.EBW -> V2XTYPE.EBW
        TYPE.AVW -> V2XTYPE.AVW
        TYPE.CLW -> V2XTYPE.CLW
        TYPE.HLW -> V2XTYPE.HLW
        TYPE.SLW -> V2XTYPE.SLW
        TYPE.RLVW -> V2XTYPE.RLVW
        TYPE.VRUCW -> V2XTYPE.VRUCW
        TYPE.GLOSA -> V2XTYPE.GLOSA
        TYPE.IVS -> V2XTYPE.IVS
        TYPE.TJW -> V2XTYPE.TJW
        TYPE.EVW -> V2XTYPE.EVW
    }
    fun mapToWarningPushed(pushed: PUSHED) = when(pushed) {
        PUSHED.DELETE -> V2XPUSHED.DELETE
        PUSHED.UPDATE -> V2XPUSHED.UPDATE
        PUSHED.ADD -> V2XPUSHED.ADD
        PUSHED.NONE -> V2XPUSHED.NONE
    }
    fun mapToWarningDirection(direction: DIRECTION, type: TYPE) = when(type) {
        TYPE.LTA,
        TYPE.ICW -> {
            if ( direction.right ) R.drawable.v2x_img_icw_car_r
            else if ( direction.left ) R.drawable.v2x_img_icw_car_l
            else if ( direction.forward ) R.drawable.v2x_img_icw_car_f
            else R.drawable.v2x_img_icw_car_r
        }
        else -> 0
    }
    fun mapToWarningSeverity(severity: SEVERITY, type:TYPE, direction: DIRECTION) = when(type) {
        TYPE.ICW -> {
            when(severity) {
                SEVERITY.L1 -> {
                    if ( direction.left ) R.drawable.v2x_ic_icw_warning_l_01
                    else R.drawable.v2x_ic_icw_warning_r_01
                }
                SEVERITY.L2 -> {
                    if ( direction.left ) R.drawable.v2x_ic_icw_warning_l_02
                    else R.drawable.v2x_ic_icw_warning_r_02
                }
                SEVERITY.L3 -> {
                    if ( direction.left ) R.drawable.v2x_ic_icw_warning_l_03
                    else R.drawable.v2x_ic_icw_warning_r_03
                }
                else -> 0
            }
        }
        else -> 0
    }
}