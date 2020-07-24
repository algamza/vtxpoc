package com.humaxdigital.automotive.v2xpoc.presentation.v2x

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.humaxdigital.automotive.v2xpoc.domain.usecases.GetCarUseCase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class TestViewModel(private val getCarUseCase: GetCarUseCase) : ViewModel() {
    private val _warningType = MutableLiveData<String>()
    private val _warningDirection = MutableLiveData<String>()
    private val _warningSeverity = MutableLiveData<String>()
    private val _warningRange = MutableLiveData<String>()
    private val _warningPushed = MutableLiveData<String>()
    private val _warningIconId = MutableLiveData<String>()
    private val _warningAudioId = MutableLiveData<String>()
    private val _warningTextId = MutableLiveData<String>()

    private val _informType = MutableLiveData<String>()
    private val _informDirection = MutableLiveData<String>()
    private val _informSeverity = MutableLiveData<String>()
    private val _informRange = MutableLiveData<String>()
    private val _informPushed = MutableLiveData<String>()
    private val _informIconId = MutableLiveData<String>()
    private val _informAudioId = MutableLiveData<String>()
    private val _informTextId = MutableLiveData<String>()

    private val _slPhase = MutableLiveData<String>()
    private val _slEnd = MutableLiveData<String>()
    private val _ltPhase = MutableLiveData<String>()
    private val _ltEnd = MutableLiveData<String>()
    private val _rtPhase = MutableLiveData<String>()
    private val _rtEnd = MutableLiveData<String>()

    private val _v2xstatus = MutableLiveData<String>()
    private val _speed = MutableLiveData<String>()
    private val _gear = MutableLiveData<String>()
    private val _light = MutableLiveData<String>()
    private val _lane = MutableLiveData<String>()
    private val _speedLimit = MutableLiveData<String>()

    val warningType : LiveData<String>
        get() = _warningType
    val warningDirection : LiveData<String>
        get() = _warningDirection
    val warningSeverity : LiveData<String>
        get() = _warningSeverity
    val warningRange : LiveData<String>
        get() = _warningRange
    val warningPushed : LiveData<String>
        get() = _warningPushed
    val warningIconId : LiveData<String>
        get() = _warningIconId
    val warningAudioId : LiveData<String>
        get() = _warningAudioId
    val warningTextId : LiveData<String>
        get() = _warningTextId

    val informType : LiveData<String>
        get() = _informType
    val informDirection : LiveData<String>
        get() = _informDirection
    val informSeverity : LiveData<String>
        get() = _informSeverity
    val informRange : LiveData<String>
        get() = _informRange
    val informPushed : LiveData<String>
        get() = _informPushed
    val informIconId : LiveData<String>
        get() = _informIconId
    val informAudioId : LiveData<String>
        get() = _informAudioId
    val informTextId : LiveData<String>
        get() = _informTextId

    val slPhase : LiveData<String>
        get() = _slPhase
    val slEnd : LiveData<String>
        get() = _slEnd
    val ltPhase : LiveData<String>
        get() = _ltPhase
    val ltEnd : LiveData<String>
        get() = _ltEnd
    val rtPhase : LiveData<String>
        get() = _rtPhase
    val rtEnd : LiveData<String>
        get() = _rtEnd

    val v2xstatus : LiveData<String>
        get() = _v2xstatus
    val speed : LiveData<String>
        get() = _speed
    val gear : LiveData<String>
        get() = _gear
    val light : LiveData<String>
        get() = _light
    val lane : LiveData<String>
        get() = _lane
    val speedLimit : LiveData<String>
        get() = _speedLimit

    init {
        fetchCar()
        registCallback()
    }

    fun fetchCar() {
    }

    fun registCallback() {
        GlobalScope.launch {
            async { callbackWaring() }
            async { callbackInform() }
            async { callbackSPat() }
            async { callbackVehicleStatus() }
        }
    }

    suspend fun callbackVehicleStatus() {
        getCarUseCase.callbackVehicleStatus().collect {value ->
            Log.d("TEST", "fetchVehicle")
            _v2xstatus.postValue(value.v2xstatus.toString())
            _speed.postValue(value.speed.toString())
            _gear.postValue(value.gear.toString())
            _light.postValue(value.light.toString())
            _lane.postValue(value.lane.toString())
            _speedLimit.postValue(value.speed_limit.toString())
        }
    }

    suspend fun callbackSPat() {
        getCarUseCase.callbackSPaT().collect {value ->
            Log.d("TEST", "fetchSPat")
            _slPhase.postValue(value.sl_phase.toString())
            _slEnd.postValue(value.sl_end.toString())
            _ltPhase.postValue(value.lt_phase.toString())
            _ltEnd.postValue(value.lt_end.toString())
            _rtPhase.postValue(value.rt_phase.toString())
            _rtEnd.postValue(value.rt_end.toString())
        }
    }

    suspend fun callbackWaring() {
        getCarUseCase.callbackWarning().collect {value ->
            Log.d("TEST", "fetchWaring")
            _warningType.postValue(value.type.toString())
            _warningDirection.postValue(value.direction.toString())
            _warningSeverity.postValue(value.severity.toString())
            _warningRange.postValue(value.range.toString())
            _warningPushed.postValue(value.pushed.toString())
            _warningIconId.postValue(value.icon_id.toString())
            _warningAudioId.postValue(value.audio_id.toString())
            _warningTextId.postValue(value.text_id.toString())
        }
    }

    suspend fun callbackInform() {
        getCarUseCase.callbackInform().collect {value ->
            Log.d("TEST", "fetchInform")
            _informType.postValue(value.type.toString())
            _informDirection.postValue(value.direction.toString())
            _informSeverity.postValue(value.severity.toString())
            _informRange.postValue(value.range.toString())
            _informPushed.postValue(value.pushed.toString())
            _informIconId.postValue(value.icon_id.toString())
            _informAudioId.postValue(value.audio_id.toString())
            _informTextId.postValue(value.text_id.toString())
        }
    }
}