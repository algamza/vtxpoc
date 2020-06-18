package com.humaxdigital.automotive.v2xpoc.presentation.v2x

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.humaxdigital.automotive.v2xpoc.domain.entities.GEAR
import com.humaxdigital.automotive.v2xpoc.domain.usecases.GetCarUseCase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class TestViewModel(private val getCarUseCase: GetCarUseCase) : ViewModel() {
    private val _status = MutableLiveData<Int>()
    private val _vehicleSpeed = MutableLiveData<Int>()
    private val _gearState = MutableLiveData<Int>()
    private val _turnLightState = MutableLiveData<Int>()
    private val _speedLimit = MutableLiveData<Int>()
    private val _warningType = MutableLiveData<Int>()
    private val _warningDirection = MutableLiveData<Int>()
    private val _warningSeverity = MutableLiveData<Int>()
    private val _warningRange = MutableLiveData<Int>()
    private val _warningPushed = MutableLiveData<Int>()
    private val _warningIconId = MutableLiveData<Int>()
    private val _warningAudioId = MutableLiveData<Int>()
    private val _warningTextId = MutableLiveData<Int>()
    private val _informType = MutableLiveData<Int>()
    private val _informDirection = MutableLiveData<Int>()
    private val _informSeverity = MutableLiveData<Int>()
    private val _informRange = MutableLiveData<Int>()
    private val _informPushed = MutableLiveData<Int>()
    private val _informIconId = MutableLiveData<Int>()
    private val _informAudioId = MutableLiveData<Int>()
    private val _informTextId = MutableLiveData<Int>()
    private val _configurationHMI = MutableLiveData<Int>()
    private val _hazardState = MutableLiveData<Int>()
    private val _breakState = MutableLiveData<Int>()
    private val _bluetoothState = MutableLiveData<Int>()

    val status : LiveData<Int>
        get() = _status
    val vehicleSpeed : LiveData<Int>
        get() = _vehicleSpeed
    val gearState : LiveData<Int>
        get() = _gearState
    val turnLightState : LiveData<Int>
        get() = _turnLightState
    val speedLimit : LiveData<Int>
        get() = _speedLimit
    val warningType : LiveData<Int>
        get() = _warningType
    val warningDirection : LiveData<Int>
        get() = _warningDirection
    val warningSeverity : LiveData<Int>
        get() = _warningSeverity
    val warningRange : LiveData<Int>
        get() = _warningRange
    val warningPushed : LiveData<Int>
        get() = _warningPushed
    val warningIconId : LiveData<Int>
        get() = _warningIconId
    val warningAudioId : LiveData<Int>
        get() = _warningAudioId
    val warningTextId : LiveData<Int>
        get() = _warningTextId
    val informType : LiveData<Int>
        get() = _informType
    val informDirection : LiveData<Int>
        get() = _informDirection
    val informSeverity : LiveData<Int>
        get() = _informSeverity
    val informRange : LiveData<Int>
        get() = _informRange
    val informPushed : LiveData<Int>
        get() = _informPushed
    val informIconId : LiveData<Int>
        get() = _informIconId
    val informAudioId : LiveData<Int>
        get() = _informAudioId
    val informTextId : LiveData<Int>
        get() = _informTextId
    val configurationHMI : LiveData<Int>
        get() = _configurationHMI
    val hazardState : LiveData<Int>
        get() = _hazardState
    val breakState : LiveData<Int>
        get() = _breakState
    val bluetoothState : LiveData<Int>
        get() = _bluetoothState

    init {
        fetchCar()
    }

    fun fetchCar() {
        GlobalScope.launch {

            getCarUseCase.getGearState().collect { value ->
                Log.d("TEST", "viewmodel="+value)
                when(value) {
                GEAR.D -> _gearState.postValue(3)
                GEAR.N -> _gearState.postValue(2)
                GEAR.P -> _gearState.postValue(1)
                GEAR.R -> _gearState.postValue(0)
            } }
        }
    }
}