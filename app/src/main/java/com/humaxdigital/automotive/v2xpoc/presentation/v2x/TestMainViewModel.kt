package com.humaxdigital.automotive.v2xpoc.presentation.v2x

import android.app.Application
import android.content.Context
import android.speech.tts.TextToSpeech
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.humaxdigital.automotive.v2xpoc.R
import com.humaxdigital.automotive.v2xpoc.domain.entities.*
import com.humaxdigital.automotive.v2xpoc.domain.usecases.GetCarUseCase
import kotlinx.android.synthetic.main.activity_test_main.view.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.conflate
import java.lang.Exception
import java.util.*

class TestMainViewModel(private val context:Context, private val getCarUseCase: GetCarUseCase, private val tts: TextToSpeech) : ViewModel() {
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

    val arr_v2x_type = context.resources.getStringArray(R.array.v2x_type)

    init {
        tts.setLanguage(Locale.CHINESE)
        fetch()
        registCallback()
    }

    fun fetch() {
        GlobalScope.launch(Dispatchers.IO) {
            fetchCar()
        }
    }

    suspend fun fetchCar() {
        return withContext(Dispatchers.IO) {
            getCarUseCase.getWarning()
                .buffer()
                .collect() {value ->
                    mapToWarningType(value.type)
                    mapToWarningDirection(value.direction)
                    mapToWarningSeverity(value.severity)
                    mapToWarningRange(value.range)
                    mapToWarningPushed(value.pushed)
                    mapToWarningIcon(value.icon_id)
                    mapToWarningAudio(value.audio_id)
                    mapToWarningText(value.text_id)
                }
            getCarUseCase.getInform()
                .buffer()
                .collect() { value ->
                    mapToInformType(value.type)
                    mapToInformDirection(value.direction)
                    mapToInformSeverity(value.severity)
                    mapToInformRange(value.range)
                    mapToInformPushed(value.pushed)
                    mapToInformIcon(value.icon_id)
                    mapToInformAudio(value.audio_id)
                    mapToInformText(value.text_id)
                }
            getCarUseCase.getVehicleStatus()
                .buffer()
                .collect() { value ->
                    mapToTurnLight(value.light)
                    mapToSpeed(value.speed)
                    mapToSpeedLimit(value.speed_limit)
                    mapToV2XStatus(value.v2xstatus)
                    mapToGear(value.gear)
                    mapToLane(value.lane)
                }
            getCarUseCase.getSPaT()
                .buffer()
                .collect() { value ->
                    Log.d("TEST", "getSPaT")
                }
        }
    }

    fun registCallback() {
        GlobalScope.launch {
            async { callbackWarning() }
            async { callbackInform() }
            async { callbackSPat() }
            async { callbackVehicleStatus() }
        }
    }

    suspend fun callbackWarning() {
        return withContext(Dispatchers.IO) {
            getCarUseCase.callbackWarning()
                .buffer()
                .collectLatest {value ->
                    mapToWarningType(value.type)
                    mapToWarningDirection(value.direction)
                    mapToWarningSeverity(value.severity)
                    mapToWarningRange(value.range)
                    mapToWarningPushed(value.pushed)
                    mapToWarningIcon(value.icon_id)
                    mapToWarningAudio(value.audio_id)
                    mapToWarningText(value.text_id)
            }
        }
    }

    suspend fun callbackInform() {
        return withContext(Dispatchers.IO) {
            getCarUseCase.callbackInform()
                .buffer()
                .collectLatest {value ->
                    mapToInformType(value.type)
                    mapToInformDirection(value.direction)
                    mapToInformSeverity(value.severity)
                    mapToInformRange(value.range)
                    mapToInformPushed(value.pushed)
                    mapToInformIcon(value.icon_id)
                    mapToInformAudio(value.audio_id)
                    mapToInformText(value.text_id)
            }
        }
    }

    suspend fun callbackSPat() {
        return withContext(Dispatchers.IO) {
            getCarUseCase.callbackSPaT()
                .buffer()
                .collectLatest {value ->
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
    }

    suspend fun callbackVehicleStatus() {
        return withContext(Dispatchers.IO) {
            getCarUseCase.callbackVehicleStatus()
                //.conflate()
                .buffer()
                .collectLatest {value ->
                    mapToTurnLight(value.light)
                    mapToSpeed(value.speed)
                    mapToSpeedLimit(value.speed_limit)
                    mapToV2XStatus(value.v2xstatus)
                    mapToGear(value.gear)
                    mapToLane(value.lane)
            }
        }
    }

    fun mapToInformText(id:Int) {
        when(id) {
            2->_text_warning.postValue(arr_v2x_type[0])
            3->_text_warning.postValue(arr_v2x_type[1])
            6->_text_warning.postValue(arr_v2x_type[2])
            7->_text_warning.postValue(arr_v2x_type[3])
            9->_text_warning.postValue(arr_v2x_type[4])
            11->_text_warning.postValue(arr_v2x_type[5])
            // 14->_text_warning.postValue(arr_v2x_type[7])
            16->_text_warning.postValue(arr_v2x_type[7])
        }
    }
    fun mapToInformAudio(id:Int) {
        when(id) {
            2->tts.speak(arr_v2x_type[0], TextToSpeech.QUEUE_ADD, null, null)
            3->tts.speak(arr_v2x_type[1], TextToSpeech.QUEUE_ADD, null, null)
            6->tts.speak(arr_v2x_type[2], TextToSpeech.QUEUE_ADD, null, null)
            7->tts.speak(arr_v2x_type[3], TextToSpeech.QUEUE_ADD, null, null)
            9->tts.speak(arr_v2x_type[4], TextToSpeech.QUEUE_ADD, null, null)
            11->tts.speak(arr_v2x_type[5], TextToSpeech.QUEUE_ADD, null, null)
            16->tts.speak(arr_v2x_type[7], TextToSpeech.QUEUE_ADD, null, null)
        }
    }
    fun mapToInformIcon(id:Int) {}
    fun mapToInformPushed(pushed: PUSHED) {}
    fun mapToInformRange(range:Int) {}
    fun mapToInformSeverity(severity:SEVERITY) {}
    fun mapToInformDirection(direction:DIRECTION) {}
    fun mapToInformType(v2x:TYPE) {}

    fun mapToWarningText(id:Int) {
        when(id) {
            2->_text_warning.postValue(arr_v2x_type[0])
            3->_text_warning.postValue(arr_v2x_type[1])
            6->_text_warning.postValue(arr_v2x_type[2])
            7->_text_warning.postValue(arr_v2x_type[3])
            9->_text_warning.postValue(arr_v2x_type[4])
            11->_text_warning.postValue(arr_v2x_type[5])
            // 14->_text_warning.postValue(arr_v2x_type[7])
            16->_text_warning.postValue(arr_v2x_type[7])
        }
    }
    fun mapToWarningAudio(id:Int) {
        when(id) {
            2->tts.speak(arr_v2x_type[0], TextToSpeech.QUEUE_ADD, null, null)
            3->tts.speak(arr_v2x_type[1], TextToSpeech.QUEUE_ADD, null, null)
            6->tts.speak(arr_v2x_type[2], TextToSpeech.QUEUE_ADD, null, null)
            7->tts.speak(arr_v2x_type[3], TextToSpeech.QUEUE_ADD, null, null)
            9->tts.speak(arr_v2x_type[4], TextToSpeech.QUEUE_ADD, null, null)
            11->tts.speak(arr_v2x_type[5], TextToSpeech.QUEUE_ADD, null, null)
            16->tts.speak(arr_v2x_type[7], TextToSpeech.QUEUE_ADD, null, null)
        }
    }
    fun mapToWarningIcon(id:Int) {}
    fun mapToWarningPushed(pushed: PUSHED) {}
    fun mapToWarningRange(range:Int) {}
    fun mapToWarningSeverity(severity:SEVERITY) {}
    fun mapToWarningDirection(direction:DIRECTION) {}
    fun mapToWarningType(v2x:TYPE) {}

    fun mapToSpeedLimit(limit:Int) {
        _text_speed_limit.postValue(limit.toString())
    }
    fun mapToSpeed(speed:Int) {
        _text_speed.postValue(speed.toString())
        _text_widget_speed.postValue(speed.toString())
    }
    fun mapToLane(lane:LANE) {}
    fun mapToV2XStatus(v2x:STATUS) {
        if ( v2x.app ) _img_widget_v2x_a.postValue(R.drawable.img_v2x_a)
        if ( v2x.v2v ) _img_widget_v2x_v.postValue(R.drawable.img_v2x_v)
        if ( v2x.v2i ) _img_widget_v2x_i.postValue(R.drawable.img_v2x_i)
        if ( v2x.gps ) _img_widget_v2x_g.postValue(R.drawable.img_v2x_g)
        if ( !v2x.app and !v2x.v2v and !v2x.v2i and !v2x.gps ) {
            _img_widget_v2x_a.postValue(0)
            _img_widget_v2x_i.postValue(0)
            _img_widget_v2x_g.postValue(0)
            _img_widget_v2x_v.postValue(0)
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
            GEAR.P->_img_widget_gear_p.postValue(R.drawable.img_gear_p)
            GEAR.N->_img_widget_gear_n.postValue(R.drawable.img_gear_n)
            GEAR.D->_img_widget_gear_d.postValue(R.drawable.img_gear_d)
            GEAR.R->_img_widget_gear_r.postValue(R.drawable.img_gear_r)

        }
    }
    fun mapToTurnLight(light:LIGHT) {
        if ( light.left ) _img_widget_light_left.postValue(R.drawable.img_light_left)
        if ( light.right ) _img_widget_light_right.postValue(R.drawable.img_light_right)
        if ( light.hazard ) _img_widget_light_hazard.postValue(R.drawable.img_light_hazard)
        if ( light.abs ) _img_widget_light_hazard.postValue(R.drawable.img_light_hazard)
        if ( light.brake ) _img_widget_light_hazard.postValue(R.drawable.img_light_hazard)
        if ( !light.left and !light.right and! light.hazard and !light.abs and !light.brake ) {
            _img_widget_light_hazard.postValue(0)
            _img_widget_light_right.postValue(0)
            _img_widget_light_left.postValue(0)
        }
    }

}