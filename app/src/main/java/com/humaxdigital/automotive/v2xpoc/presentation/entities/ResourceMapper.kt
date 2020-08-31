package com.humaxdigital.automotive.v2xpoc.presentation.entities

import com.humaxdigital.automotive.v2xpoc.R
import com.humaxdigital.automotive.v2xpoc.domain.entities.*

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
    fun mapToWarningText(index: Int) = when(index) {
        10 -> R.string.FCW_WarnText_Ahead
        20 -> R.string.ICW_InfromText_Left
        21 -> R.string.ICW_WarnText_Left
        22 -> R.string.ICW_WarninText_Left_Corner
        23 -> R.string.ICW_InfromText_Right
        24 -> R.string.ICW_WarnText_Right
        25 -> R.string.ICW_WarnText_Right_Corner
        30 -> R.string.LTA_WarnText_Left
        31 -> R.string.LTA_WarnText_Right
        32 -> R.string.LTA_WarnText_Across
        40 -> R.string.BSW_InfromText_Left
        41 -> R.string.BSW_WarnText_Left
        42 -> R.string.BSW_InfromText_Right
        43 -> R.string.BSW_WarnText_Right
        60 -> R.string.EBW_WarnText
        70 -> R.string.AVW_WarnText_ESPLgt
        71 -> R.string.AVW_WarnText_ABSLgt
        72 -> R.string.AVW_WarnText_EmergencyLgt
        80 -> R.string.HLW_RWW_WarnText
        100 -> R.string.RLVW_InformText_ToRed
        101 -> R.string.RLVW_WarnText_ToRed
        102 -> R.string.RLVW_WarnText_Red
        103 -> R.string.RLVW_InformText_ToGreen
        110 -> R.string.EVW_WarnText_Behind
        111 -> R.string.EVW_WarnText_BehindLeft
        112 -> R.string.EVW_WarnText_BEhindRight
        113 -> R.string.EVW_WarnText_SlowDown
        114 -> R.string.EVW_WarnText_FarBehind
        120 -> R.string.VRUCW_WarnText_Left
        121 -> R.string.VRUCW_WarnText_Right
        else -> R.string.empty
    }
    fun mapToWarningAudio(index: Int) = when(index) {
        10 -> R.raw.fcw_beep
        20 -> R.raw.icw_beep
        30 -> R.raw.lta_beep
        40 -> R.raw.bsw_beep
        60 -> R.raw.ebw_beep
        70 -> R.raw.avw_beep
        80 -> R.raw.hlw_rww_beep
        100 -> R.raw.rlvw_beep
        110 -> R.raw.evw_beep
        120 -> R.raw.vrucw_beep
        else -> 0
    }
    fun mapToWarningVoice(index: Int) = when(index) {
        81 -> R.string.HLW_RWW_WarnText
        101 -> R.string.RLVW_WarnText_Red
        102 -> R.string.RLVW_InformText_ToGreen
        111 -> R.string.EVW_WarnText_FarBehind
        else -> R.string.empty
    }

    fun mapToWarningIcon(index: Int) = when(index) {
        11 -> R.drawable.v2x_img_car_warning_black
        10 -> R.drawable.v2x_img_car_warning_red
        20 -> R.drawable.v2x_ic_icw_warning_l_01
        21 -> R.drawable.v2x_ic_icw_warning_l_02
        22 -> R.drawable.v2x_ic_icw_warning_l_03
        23 -> R.drawable.v2x_ic_icw_warning_r_01
        24 -> R.drawable.v2x_ic_icw_warning_r_02
        25 -> R.drawable.v2x_ic_icw_warning_r_03
        30 -> R.drawable.v2x_img_icw_car_l
        31 -> R.drawable.v2x_img_icw_car_r
        32 -> R.drawable.v2x_img_icw_car_f
        40, 41 -> R.drawable.v2x_img_pop_bsw_l
        42, 43 -> R.drawable.v2x_img_pop_bsw_r
        63, 64, 65 -> R.drawable.v2x_img_level_warning_n
        60, 61, 62 -> R.drawable.v2x_img_level_warning_s
        70, 71 -> R.drawable.v2x_img_level_warning_n
        80 -> R.drawable.v2x_ic_corporation
        81 -> R.drawable.v2x_img_rww_left
        82 -> R.drawable.v2x_img_rww_right
        100 -> 0
        101 -> 0
        102 -> 0
        103 -> 0
        110 -> R.drawable.v2x_img_pop_center_01
        111 -> R.drawable.v2x_img_pop_left_01
        112 -> R.drawable.v2x_img_pop_right_01
        113 -> R.drawable.v2x_img_pop_center_02
        114 -> R.drawable.v2x_img_pop_left_02
        115 -> R.drawable.v2x_img_pop_right_02
        120 -> 0
        121 -> 0
        201 -> 0
        202 -> 0
        204 -> 0
        208 -> 0
        216 -> 0
        220 -> 0
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