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

        testmodel.status.observe(this, Observer { bind.status.text = it.toString() })
        testmodel.vehicleSpeed.observe(this, Observer { bind.vehicleSpeed.text = it.toString() })
        testmodel.gearState.observe(this, Observer { bind.gearState.text = it.toString() })
        testmodel.turnLightState.observe(this, Observer { bind.turnLightState.text = it.toString() })
        testmodel.speedLimit.observe(this, Observer { bind.speedLimit.text = it.toString() })
        testmodel.configurationHMI.observe(this, Observer { bind.configurationHMI.text = it.toString() })
        testmodel.hazardState.observe(this, Observer { bind.hazardState.text = it.toString() })
        testmodel.breakState.observe(this, Observer { bind.breakState.text = it.toString() })
        testmodel.bluetoothState.observe(this, Observer { bind.bluetoothState.text = it.toString() })
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
    }
}
