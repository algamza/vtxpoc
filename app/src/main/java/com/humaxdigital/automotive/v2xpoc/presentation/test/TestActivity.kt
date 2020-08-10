package com.humaxdigital.automotive.v2xpoc.presentation.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.humaxdigital.automotive.v2xpoc.R
import com.humaxdigital.automotive.v2xpoc.databinding.ActivityTestBinding
import kotlinx.android.synthetic.main.activity_test.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class TestActivity : AppCompatActivity() {

    private val testmodel: TestViewModel by viewModel()
    private val bind by lazy { DataBindingUtil.setContentView<ActivityTestBinding>(this, R.layout.activity_test) }
    private lateinit var animation : Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind.test = testmodel
        bind.setLifecycleOwner(this)

        animation = AlphaAnimation(1.0f, 0.0f)
        animation.duration = 200
        animation.interpolator = LinearInterpolator()
        animation.repeatCount = 1
        animation.repeatMode = Animation.REVERSE

        testmodel.inform.observe(this, Observer {
            inform.startAnimation(animation)
        })
        testmodel.warning.observe(this, Observer {
            warning.startAnimation(animation)
        })
        testmodel.vehicle.observe(this, Observer {
            vehicle.startAnimation(animation)
        })
        testmodel.spat.observe(this, Observer {
            spat.startAnimation(animation)
        })
        testmodel.hv_pos.observe(this, Observer {
            hvpos.startAnimation(animation)
        })
        testmodel.hv_motion.observe(this, Observer {
            hvmotion.startAnimation(animation)
        })
        testmodel.rv1.observe(this, Observer {
            rv1.startAnimation(animation)
        })
        testmodel.rv2.observe(this, Observer {
            rv2.startAnimation(animation)
        })
        testmodel.rv3.observe(this, Observer {
            rv3.startAnimation(animation)
        })
        testmodel.rv4.observe(this, Observer {
            rv4.startAnimation(animation)
        })
    }
}
