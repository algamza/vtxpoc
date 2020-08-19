package com.humaxdigital.automotive.v2xpoc.presentation.v2x

import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.humaxdigital.automotive.v2xpoc.R

@BindingAdapter("id_severity_icon")
fun idSeverityIcon(view: ImageView, res:Int) {
    var param = view.layoutParams as ViewGroup.MarginLayoutParams
    when(res) {
        R.drawable.v2x_ic_icw_warning_l_03,
        R.drawable.v2x_ic_icw_warning_l_02,
        R.drawable.v2x_ic_icw_warning_l_01 -> { param.leftMargin = 0 }
        R.drawable.v2x_ic_icw_warning_r_03,
        R.drawable.v2x_ic_icw_warning_r_02,
        R.drawable.v2x_ic_icw_warning_r_01 -> { param.leftMargin = 1095 }
    }
    view.requestLayout()
    Glide.with(view.context).load(res).into(view)
}

@BindingAdapter("id_lta_direction_icon")
fun idLTADirectionIcon(view: ImageView, res:Int) {
    var param = view.layoutParams as ViewGroup.MarginLayoutParams
    when(res) {
        R.drawable.v2x_img_icw_car_l -> {
            param.width = 278
            param.height = 124
            param.leftMargin = 82
            param.topMargin = 172
        }
        R.drawable.v2x_img_icw_car_r -> {
            param.width = 278
            param.height = 124
            param.leftMargin = 826
            param.topMargin = 172
        }
        R.drawable.v2x_img_icw_car_f -> {
            param.width = 161
            param.height = 113
            param.leftMargin = 562
            param.topMargin = 110
        }
    }
    Glide.with(view.context).load(res).into(view)
}

@BindingAdapter("id_icw_direction_icon")
fun idICWDirectionIcon(view: ImageView, res:Int) {
    var param = view.layoutParams as ViewGroup.MarginLayoutParams
    when(res) {
        R.drawable.v2x_img_icw_car_l -> { param.leftMargin = 189 }
        R.drawable.v2x_img_icw_car_r -> { param.leftMargin = 813 }
    }
    Glide.with(view.context).load(res).into(view)
}

@BindingAdapter("id_warning_icon")
fun idWarningIcon(view: ImageView, res:Int) {
    Glide.with(view.context).load(res).into(view)
}

@BindingAdapter("id_range")
fun idRange(view: ImageView, res:Int) {
    Glide.with(view.context).load(res).into(view)
}

@BindingAdapter("id_light_left")
fun idLightLeft(view: ImageView, res:Int) {
    Glide.with(view.context).load(res).into(view)
}
@BindingAdapter("id_light_right")
fun idLightRight(view: ImageView, res:Int) {
    Glide.with(view.context).load(res).into(view)
}
@BindingAdapter("id_light_hazard")
fun idLightHazard(view: ImageView, res:Int) {
    Glide.with(view.context).load(res).into(view)
}
@BindingAdapter("id_light_brake")
fun idLightBrake(view: ImageView, res:Int) {
    Glide.with(view.context).load(res).into(view)
}

@BindingAdapter("id_gear_p")
fun idGearP(view: ImageView, res:Int) {
    Glide.with(view.context).load(res).into(view)
}

@BindingAdapter("id_gear_r")
fun idGearR(view: ImageView, res:Int) {
    Glide.with(view.context).load(res).into(view)
}

@BindingAdapter("id_gear_n")
fun idGearN(view: ImageView, res:Int) {
    Glide.with(view.context).load(res).into(view)
}

@BindingAdapter("id_gear_d")
fun idGearD(view: ImageView, res:Int) {
    Glide.with(view.context).load(res).into(view)
}

@BindingAdapter("id_v2x_status_g")
fun idV2XStatusG(view: ImageView, res:Int) {
    Glide.with(view.context).load(res).into(view)
}

@BindingAdapter("id_v2x_status_v")
fun idV2XStatusV(view: ImageView, res:Int) {
    Glide.with(view.context).load(res).into(view)
}

@BindingAdapter("id_v2x_status_i")
fun idV2XStatusI(view: ImageView, res:Int) {
    Glide.with(view.context).load(res).into(view)
}

@BindingAdapter("id_v2x_status_a")
fun idV2XStatusA(view: ImageView, res:Int) {
    Glide.with(view.context).load(res).into(view)
}