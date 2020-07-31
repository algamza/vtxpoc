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
    private val _warning = MutableLiveData<String>()
    private val _inform = MutableLiveData<String>()
    private val _vehicle = MutableLiveData<String>()
    private val _spat = MutableLiveData<String>()
    private val _hv_pos = MutableLiveData<String>()
    private val _hv_motion = MutableLiveData<String>()
    private val _rv1 = MutableLiveData<String>()
    private val _rv2 = MutableLiveData<String>()
    private val _rv3 = MutableLiveData<String>()
    private val _rv4 = MutableLiveData<String>()

    val warning : LiveData<String>
        get() = _warning
    val inform : LiveData<String>
        get() = _inform
    val vehicle : LiveData<String>
        get() = _vehicle
    val spat : LiveData<String>
        get() = _spat
    val hv_pos : LiveData<String>
        get() = _hv_pos
    val hv_motion : LiveData<String>
        get() = _hv_motion
    val rv1 : LiveData<String>
        get() = _rv1
    val rv2 : LiveData<String>
        get() = _rv2
    val rv3 : LiveData<String>
        get() = _rv3
    val rv4 : LiveData<String>
        get() = _rv4


    init {
        fetchCar()
        registCallback()
    }

    fun fetchCar() {
        GlobalScope.launch {
            getCarUseCase.getWarning().collect {it -> _warning.postValue(it.toString()) }
            getCarUseCase.getInform().collect {it -> _inform.postValue(it.toString()) }
            getCarUseCase.getVehicleStatus().collect {it -> _vehicle.postValue(it.toString()) }
            getCarUseCase.getSPaT().collect {it -> _spat.postValue(it.toString()) }
            getCarUseCase.getHVPos().collect {it -> _hv_pos.postValue(it.toString()) }
            getCarUseCase.getHVMotion().collect {it -> _hv_motion.postValue(it.toString()) }
            getCarUseCase.getRV1Status().collect {it -> _rv1.postValue(it.toString()) }
            getCarUseCase.getRV2Status().collect {it -> _rv2.postValue(it.toString()) }
            getCarUseCase.getRV3Status().collect {it -> _rv3.postValue(it.toString()) }
            getCarUseCase.getRV4Status().collect {it -> _rv4.postValue(it.toString()) }
        }
    }

    fun registCallback() {
        GlobalScope.launch {
            async { callbackWaring() }
            async { callbackInform() }
            async { callbackSPat() }
            async { callbackVehicleStatus() }
            async { callbackHVPos() }
            async { callbackHVMotion() }
            async { callbackRV1() }
            async { callbackRV2() }
            async { callbackRV3() }
            async { callbackRV4() }
        }
    }



    suspend fun callbackWaring() {
        getCarUseCase.callbackWarning().collect { value -> _warning.postValue(value.toString().substring(value.toString().indexOf("(")+1, value.toString().lastIndex))}
    }

    suspend fun callbackInform() {
        getCarUseCase.callbackInform().collect {value ->
            _inform.postValue(value.toString().substring(value.toString().indexOf("(")+1, value.toString().lastIndex))
        }
    }

    suspend fun callbackVehicleStatus() {
        getCarUseCase.callbackVehicleStatus().collect { value -> _vehicle.postValue(value.toString().substring(value.toString().indexOf("(")+1, value.toString().lastIndex)) }
    }

    suspend fun callbackSPat() {
        getCarUseCase.callbackSPaT().collect { value -> _spat.postValue(value.toString().substring(value.toString().indexOf("(")+1, value.toString().lastIndex)) }
    }

    suspend fun callbackHVPos() {
        getCarUseCase.callbackHVPos().collect { value -> _hv_pos.postValue(value.toString().substring(value.toString().indexOf("(")+1, value.toString().lastIndex)) }
    }

    suspend fun callbackHVMotion() {
        getCarUseCase.callbackHVMotion().collect { value -> _hv_motion.postValue(value.toString().substring(value.toString().indexOf("(")+1, value.toString().lastIndex)) }
    }

    suspend fun callbackRV1() {
        getCarUseCase.callbackRV1Status().collect { value -> _rv1.postValue(value.toString().substring(value.toString().indexOf("(")+1, value.toString().lastIndex)) }
    }
    suspend fun callbackRV2() {
        getCarUseCase.callbackRV2Status().collect { value -> _rv2.postValue(value.toString().substring(value.toString().indexOf("(")+1, value.toString().lastIndex)) }
    }
    suspend fun callbackRV3() {
        getCarUseCase.callbackRV3Status().collect { value -> _rv3.postValue(value.toString().substring(value.toString().indexOf("(")+1, value.toString().lastIndex)) }
    }
    suspend fun callbackRV4() {
        getCarUseCase.callbackRV4Status().collect { value -> _rv4.postValue(value.toString().substring(value.toString().indexOf("(")+1, value.toString().lastIndex)) }
    }
}