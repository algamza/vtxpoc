package com.humaxdigital.automotive.v2xpoc.presentation.v2x

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.humaxdigital.automotive.v2xpoc.R
import com.humaxdigital.automotive.v2xpoc.domain.entities.GEAR
import com.humaxdigital.automotive.v2xpoc.domain.entities.LANE
import com.humaxdigital.automotive.v2xpoc.domain.entities.TURNLIGHT
import com.humaxdigital.automotive.v2xpoc.domain.entities.V2XSTATE
import com.humaxdigital.automotive.v2xpoc.domain.usecases.GetCarUseCase
import kotlinx.android.synthetic.main.activity_test_main.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class TestMainViewModel(private val getCarUseCase: GetCarUseCase) : ViewModel() {
    private val _img_back = MutableLiveData<Int>()

    private val _img_widget_light_left = MutableLiveData<Int>()
    private val _img_widget_light_right = MutableLiveData<Int>()
    private val _img_widget_light_hazard = MutableLiveData<Int>()

    private val _text_widget_speed = MutableLiveData<String>()

    private val _img_widget_gear_p = MutableLiveData<Int>()
    private val _img_widget_gear_r = MutableLiveData<Int>()
    private val _img_widget_gear_n = MutableLiveData<Int>()
    private val _img_widget_gear_d = MutableLiveData<Int>()

    private val _img_widget_v2x_g = MutableLiveData<Int>()
    private val _img_widget_v2x_v = MutableLiveData<Int>()
    private val _img_widget_v2x_i = MutableLiveData<Int>()
    private val _img_widget_v2x_a = MutableLiveData<Int>()

    private val _img_light_traffic_st = MutableLiveData<Int>()
    private val _img_light_traffic_left = MutableLiveData<Int>()
    private val _img_light_traffic_right = MutableLiveData<Int>()

    private val _text_speed = MutableLiveData<String>()
    private val _text_speed_limit = MutableLiveData<String>()

    private val _img_brake_light = MutableLiveData<Int>()

    private val _img_hlw = MutableLiveData<Int>()
    private val _text_hlw = MutableLiveData<String>()

    private val _text_warning = MutableLiveData<String>()
    private val _img_warning = MutableLiveData<Int>()

    private val _img_ivs_1 = MutableLiveData<Int>()
    private val _img_ivs_2 = MutableLiveData<Int>()


    val img_back : LiveData<Int>
        get() = _img_back

    val img_widget_light_left : LiveData<Int>
        get() = _img_widget_light_left
    val img_widget_light_right : LiveData<Int>
        get() = _img_widget_light_right
    val img_widget_light_hazard : LiveData<Int>
        get() = _img_widget_light_hazard

    val text_widget_speed : LiveData<String>
        get() = _text_widget_speed

    val img_widget_gear_p : LiveData<Int>
        get() = _img_widget_gear_p
    val img_widget_gear_r : LiveData<Int>
        get() = _img_widget_gear_r
    val img_widget_gear_n : LiveData<Int>
        get() = _img_widget_gear_n
    val img_widget_gear_d : LiveData<Int>
        get() = _img_widget_gear_d

    val img_widget_v2x_g : LiveData<Int>
        get() = _img_widget_v2x_g
    val img_widget_v2x_v : LiveData<Int>
        get() = _img_widget_v2x_v
    val img_widget_v2x_i : LiveData<Int>
        get() = _img_widget_v2x_i
    val img_widget_v2x_a : LiveData<Int>
        get() = _img_widget_v2x_a

    val img_light_traffic_st : LiveData<Int>
        get() = _img_light_traffic_st
    val img_light_traffic_left : LiveData<Int>
        get() = _img_light_traffic_left
    val img_light_traffic_right : LiveData<Int>
        get() = _img_light_traffic_right

    val text_speed : LiveData<String>
        get() = _text_speed
    val text_speed_limit : LiveData<String>
        get() = _text_speed_limit

    val img_brake_light : LiveData<Int>
        get() = _img_brake_light

    val img_hlw : LiveData<Int>
        get() = _img_hlw
    val text_hlw : LiveData<String>
        get() = _text_hlw

    val text_warning : LiveData<String>
        get() = _text_warning
    val img_warning : LiveData<Int>
        get() = _img_warning

    val img_ivs_1 : LiveData<Int>
        get() = _img_ivs_1
    val img_ivs_2 : LiveData<Int>
        get() = _img_ivs_2

    init {
        fetchCar()
    }

    fun fetchCar() {
        GlobalScope.launch { fetchWaring() }
        GlobalScope.launch { fetchInform() }
        GlobalScope.launch { fetchSPat() }
        GlobalScope.launch { fetchVehicle() }
    }

    suspend fun fetchVehicle() {
        getCarUseCase.getVehicle().collect {value ->
            mapToTurnLight(value.light)
            mapToSpeed(value.speed)
            mapToSpeedLimit(value.speed_limit)
            mapToV2XStatus(value.v2xstatus)
            mapToGear(value.gear)
            mapToLane(value.lane)
        }
    }

    fun mapToSpeedLimit(limit:Int) {
        _text_speed_limit.postValue(limit.toString())
    }
    fun mapToSpeed(speed:Int) {
        _text_speed.postValue(speed.toString())
        _text_widget_speed.postValue(speed.toString())
    }

    fun mapToLane(lane:LANE) {
    }
    fun mapToV2XStatus(v2x:V2XSTATE) {
        when(v2x) {
            V2XSTATE.APP->{
                _img_widget_v2x_a.postValue(R.drawable.img_v2x_a)
                _img_widget_v2x_i.postValue(0)
                _img_widget_v2x_g.postValue(0)
                _img_widget_v2x_v.postValue(0)
            }
            V2XSTATE.V2V->{
                _img_widget_v2x_a.postValue(0)
                _img_widget_v2x_i.postValue(0)
                _img_widget_v2x_g.postValue(0)
                _img_widget_v2x_v.postValue(R.drawable.img_v2x_v)
            }
            V2XSTATE.V2I->{
                _img_widget_v2x_a.postValue(0)
                _img_widget_v2x_i.postValue(R.drawable.img_v2x_i)
                _img_widget_v2x_g.postValue(0)
                _img_widget_v2x_v.postValue(0)
            }
            V2XSTATE.GPS->{
                _img_widget_v2x_a.postValue(0)
                _img_widget_v2x_i.postValue(0)
                _img_widget_v2x_g.postValue(R.drawable.img_v2x_g)
                _img_widget_v2x_v.postValue(0)
            }
            V2XSTATE.NONE->{
                _img_widget_v2x_a.postValue(0)
                _img_widget_v2x_i.postValue(0)
                _img_widget_v2x_g.postValue(0)
                _img_widget_v2x_v.postValue(0)
            }
        }
    }
    fun mapToGear(gear:GEAR) {
        when(gear) {
            GEAR.NONE->{
                _img_widget_gear_d.postValue(0)
                _img_widget_gear_n.postValue(0)
                _img_widget_gear_p.postValue(0)
                _img_widget_gear_r.postValue(0)
            }
            GEAR.P->{
                _img_widget_gear_d.postValue(0)
                _img_widget_gear_n.postValue(0)
                _img_widget_gear_p.postValue(R.drawable.img_gear_p)
                _img_widget_gear_r.postValue(0)
            }
            GEAR.N->{
                _img_widget_gear_d.postValue(0)
                _img_widget_gear_n.postValue(R.drawable.img_gear_n)
                _img_widget_gear_p.postValue(0)
                _img_widget_gear_r.postValue(0)
            }
            GEAR.D->{
                _img_widget_gear_d.postValue(R.drawable.img_gear_d)
                _img_widget_gear_n.postValue(0)
                _img_widget_gear_p.postValue(0)
                _img_widget_gear_r.postValue(0)
            }
            GEAR.R->{
                _img_widget_gear_d.postValue(0)
                _img_widget_gear_n.postValue(0)
                _img_widget_gear_p.postValue(0)
                _img_widget_gear_r.postValue(R.drawable.img_gear_r)
            }
        }
    }

    fun mapToTurnLight(light:TURNLIGHT) {
        when(light) {
            TURNLIGHT.LEFT -> {
                _img_widget_light_hazard.postValue(0)
                _img_widget_light_right.postValue(0)
                _img_widget_light_left.postValue(R.drawable.img_light_left)
            }
            TURNLIGHT.RIGHT -> {
                _img_widget_light_hazard.postValue(0)
                _img_widget_light_right.postValue(R.drawable.img_light_right)
                _img_widget_light_left.postValue(0)
            }
            TURNLIGHT.HAZARD -> {
                _img_widget_light_hazard.postValue(R.drawable.img_light_hazard)
                _img_widget_light_right.postValue(0)
                _img_widget_light_left.postValue(0)
            }
            TURNLIGHT.OFF -> {
                _img_widget_light_hazard.postValue(0)
                _img_widget_light_right.postValue(0)
                _img_widget_light_left.postValue(0)
            }
        }
    }

    suspend fun fetchSPat() {
        getCarUseCase.getSPaT().collect {value ->
            Log.d("TEST", "fetchSPat")
            /*
            _slPhase.postValue(value.sl_phase.toString())
            _slEnd.postValue(value.sl_end.toString())
            _ltPhase.postValue(value.lt_phase.toString())
            _ltEnd.postValue(value.lt_end.toString())
            _rtPhase.postValue(value.rt_phase.toString())
            _rtEnd.postValue(value.rt_end.toString())

             */
        }
    }

    suspend fun fetchWaring() {
        getCarUseCase.getWarning().collect {value ->
            Log.d("TEST", "fetchWaring")
            /*
            _warningType.postValue(value.type.toString())
            _warningDirection.postValue(value.direction.toString())
            _warningSeverity.postValue(value.severity.toString())
            _warningRange.postValue(value.range.toString())
            _warningPushed.postValue(value.pushed.toString())
            _warningIconId.postValue(value.icon_id.toString())
            _warningAudioId.postValue(value.audio_id.toString())
            _warningTextId.postValue(value.text_id.toString())

             */
        }
    }

    suspend fun fetchInform() {
        getCarUseCase.getInform().collect {value ->
            Log.d("TEST", "fetchInform")
            /*
            _informType.postValue(value.type.toString())
            _informDirection.postValue(value.direction.toString())
            _informSeverity.postValue(value.severity.toString())
            _informRange.postValue(value.range.toString())
            _informPushed.postValue(value.pushed.toString())
            _informIconId.postValue(value.icon_id.toString())
            _informAudioId.postValue(value.audio_id.toString())
            _informTextId.postValue(value.text_id.toString())

             */
        }
    }
}