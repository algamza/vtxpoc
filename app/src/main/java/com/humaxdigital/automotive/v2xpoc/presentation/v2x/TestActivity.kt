package com.humaxdigital.automotive.v2xpoc.presentation.v2x

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.humaxdigital.automotive.v2xpoc.R
import com.humaxdigital.automotive.v2xpoc.databinding.ActivityTestBinding
import org.koin.android.viewmodel.ext.android.viewModel

class TestActivity : AppCompatActivity() {

    private val testmodel: TestViewModel by viewModel()
    private val bind by lazy { DataBindingUtil.setContentView<ActivityTestBinding>(this, R.layout.activity_test) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bind.test = testmodel
        bind.setLifecycleOwner(this)

        /*
        testmodel.warningType.observe(this, Observer { bind.warningType.text = it.toString() })
        testmodel.warningDirection.observe(this, Observer { bind.warningDirection.text = it.toString() })
        testmodel.warningSeverity.observe(this, Observer { bind.warningSeverity.text = it.toString() })
        testmodel.warningRange.observe(this, Observer { bind.warningRange.text = it.toString() })
        testmodel.warningPushed.observe(this, Observer { bind.warningPushed.text = it.toString() })
        testmodel.warningIconId.observe(this, Observer { bind.warningIconId.text = it.toString() })
        testmodel.warningAudioId.observe(this, Observer { bind.warningAudioId.text = it.toString() })
        testmodel.warningTextId.observe(this, Observer { bind.warningTextId.text = it.toString() })
        testmodel.informType.observe(this, Observer { bind.informType.text = it.toString() })
        testmodel.informDirection.observe(this, Observer { bind.informDirection.text = it.toString() })
        testmodel.informSeverity.observe(this, Observer { bind.informSeverity.text = it.toString() })
        testmodel.informRange.observe(this, Observer { bind.informRange.text = it.toString() })
        testmodel.informPushed.observe(this, Observer { bind.informPushed.text = it.toString() })
        testmodel.informIconId.observe(this, Observer { bind.informIconId.text = it.toString() })
        testmodel.informAudioId.observe(this, Observer { bind.informAudioId.text = it.toString() })
        testmodel.informTextId.observe(this, Observer { bind.informTextId.text = it.toString() })
        testmodel.slPhase.observe(this, Observer { bind.slPhase.text = it.toString() })
        testmodel.slEnd.observe(this, Observer { bind.slEnd.text = it.toString() })
        testmodel.ltPhase.observe(this, Observer { bind.ltPhase.text = it.toString() })
        testmodel.ltEnd.observe(this, Observer { bind.ltEnd.text = it.toString() })
        testmodel.rtPhase.observe(this, Observer { bind.rtPhase.text = it.toString() })
        testmodel.rtEnd.observe(this, Observer { bind.rtEnd.text = it.toString() })
        testmodel.v2xstatus.observe(this, Observer { bind.v2xstatus.text = it.toString() })
        testmodel.speed.observe(this, Observer { bind.speed.text = it.toString() })
        testmodel.gear.observe(this, Observer { bind.gear.text = it.toString() })
        testmodel.light.observe(this, Observer { bind.light.text = it.toString() })
        testmodel.lane.observe(this, Observer { bind.lane.text = it.toString() })
        testmodel.speedLimit.observe(this, Observer { bind.speedLimit.text = it.toString() })

         */
    }
}
