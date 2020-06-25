package com.humaxdigital.automotive.v2xpoc.presentation.v2x

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("img_back")
fun imgBack(view: ImageView, res:Int) {
    Glide.with(view.context).load(res).into(view)
}

@BindingAdapter("img_light_left")
fun imgLightLeft(view: ImageView, res:Int) {
    Glide.with(view.context).load(res).into(view)
}

@BindingAdapter("img_light_hazard")
fun imgLightHazard(view: ImageView, res:Int) {
    Glide.with(view.context).load(res).into(view)
}

@BindingAdapter("img_light_right")
fun imgLightRight(view: ImageView, res:Int) {
    Glide.with(view.context).load(res).into(view)
}

@BindingAdapter("img_gear_p")
fun imgGearP(view: ImageView, res:Int) {
    Glide.with(view.context).load(res).into(view)
}

@BindingAdapter("img_gear_r")
fun imgGearR(view: ImageView, res:Int) {
    Glide.with(view.context).load(res).into(view)
}

@BindingAdapter("img_gear_n")
fun imgGearN(view: ImageView, res:Int) {
    Glide.with(view.context).load(res).into(view)
}

@BindingAdapter("img_gear_d")
fun imgGearD(view: ImageView, res:Int) {
    Glide.with(view.context).load(res).into(view)
}

@BindingAdapter("img_v2x_g")
fun imgV2xG(view: ImageView, res:Int) {
    Glide.with(view.context).load(res).into(view)
}

@BindingAdapter("img_v2x_v")
fun imgV2xV(view: ImageView, res:Int) {
    Glide.with(view.context).load(res).into(view)
}

@BindingAdapter("img_v2x_i")
fun imgV2xI(view: ImageView, res:Int) {
    Glide.with(view.context).load(res).into(view)
}

@BindingAdapter("img_v2x_a")
fun imgV2xA(view: ImageView, res:Int) {
    Glide.with(view.context).load(res).into(view)
}