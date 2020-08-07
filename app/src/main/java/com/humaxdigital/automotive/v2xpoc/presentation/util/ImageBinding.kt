package com.humaxdigital.automotive.v2xpoc.presentation.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

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