package com.humaxdigital.automotive.v2xpoc.presentation.v2x

import android.content.Context
import android.speech.tts.TextToSpeech
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.humaxdigital.automotive.v2xpoc.domain.entities.*
import com.humaxdigital.automotive.v2xpoc.domain.usecases.GetCarUseCase
import com.humaxdigital.automotive.v2xpoc.presentation.entities.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*

class MainViewModel(private val context: Context, private val getCarUseCase: GetCarUseCase, public val tts: TextToSpeech) : ViewModel() {
    private val TAG = this.javaClass.name

    // test livedata
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
    private val _track_obj1 = MutableLiveData<String>()
    private val _track_obj2 = MutableLiveData<String>()
    private val _track_obj3 = MutableLiveData<String>()
    private val _wow = MutableLiveData<String>()
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
    val track_obj1 : LiveData<String>
        get() = _track_obj1
    val track_obj2 : LiveData<String>
        get() = _track_obj2
    val track_obj3 : LiveData<String>
        get() = _track_obj3
    val wow : LiveData<String>
        get() = _wow

    // resource livedata
    private val _id_range = MutableLiveData<Int>()
    private val _id_v2x_status_g = MutableLiveData<Int>()
    private val _id_v2x_status_v = MutableLiveData<Int>()
    private val _id_v2x_status_i = MutableLiveData<Int>()
    private val _id_v2x_status_a = MutableLiveData<Int>()
    private val _id_gear_p = MutableLiveData<Int>()
    private val _id_gear_r = MutableLiveData<Int>()
    private val _id_gear_n = MutableLiveData<Int>()
    private val _id_gear_d = MutableLiveData<Int>()
    private val _vehicle_speed = MutableLiveData<Int>()
    private val _id_light_left = MutableLiveData<Int>()
    private val _id_light_right = MutableLiveData<Int>()
    private val _id_light_hazard = MutableLiveData<Int>()
    private val _id_car_light_brake = MutableLiveData<Int>()
    private val _warning_type = MutableLiveData<V2XTYPE>()
    private val _warning_pused = MutableLiveData<V2XPUSHED>()
    private val _warning_distance = MutableLiveData<String>()
    private val _warning_text = MutableLiveData<String>()
    private val _id_warning_icon = MutableLiveData<Int>()
    private val _warning_audio = MutableLiveData<Int>()
    private val _warning_voice = MutableLiveData<String>()
    private val _id_warning_direction = MutableLiveData<Int>()
    private val _id_warning_severity = MutableLiveData<Int>()

    val warning_type : LiveData<V2XTYPE>
        get() = _warning_type
    val warning_pused : LiveData<V2XPUSHED>
        get() = _warning_pused
    val warning_text : LiveData<String>
        get() = _warning_text
    val id_warning_icon : LiveData<Int>
        get() = _id_warning_icon
    val warning_audio : LiveData<Int>
        get() = _warning_audio
    val warning_voice : LiveData<String>
        get() = _warning_voice
    val warning_distance : LiveData<String>
        get() = _warning_distance
    val id_range : LiveData<Int>
        get() = _id_range
    val id_v2x_status_g : LiveData<Int>
        get() = _id_v2x_status_g
    val id_v2x_status_v : LiveData<Int>
        get() = _id_v2x_status_v
    val id_v2x_status_i : LiveData<Int>
        get() = _id_v2x_status_i
    val id_v2x_status_a : LiveData<Int>
        get() = _id_v2x_status_a
    val id_gear_p : LiveData<Int>
        get() = _id_gear_p
    val id_gear_r : LiveData<Int>
        get() = _id_gear_r
    val id_gear_n : LiveData<Int>
        get() = _id_gear_n
    val id_gear_d : LiveData<Int>
        get() = _id_gear_d
    val vehicle_speed : LiveData<Int>
        get() = _vehicle_speed
    val id_light_left : LiveData<Int>
        get() = _id_light_left
    val id_light_right : LiveData<Int>
        get() = _id_light_right
    val id_light_hazard : LiveData<Int>
        get() = _id_light_hazard
    val id_car_light_brake : LiveData<Int>
        get() = _id_car_light_brake
    val id_warning_direction : LiveData<Int>
        get() = _id_warning_direction
    val id_warning_severity : LiveData<Int>
        get() = _id_warning_severity

    init {
        tts.setLanguage(Locale.CHINESE)
        fetchCar()
        registCallback()
    }

    private fun fetchCar() {
        GlobalScope.launch {
            getCarUseCase.getWarning().collect { it -> updateWarning(it) }
            getCarUseCase.getInform().collect { it -> updateInform(it) }
            getCarUseCase.getVehicleStatus().collect { it -> updateVehicleStatus(it) }
            getCarUseCase.getSPaT().collect { it -> updateSPaT(it) }
            getCarUseCase.getHVPos().collect { it -> updateHVPos(it) }
            getCarUseCase.getHVMotion().collect { it -> updateHVMotion(it) }
            getCarUseCase.getRV1Status().collect { it -> updateRV1Status(it) }
            getCarUseCase.getRV2Status().collect { it -> updateRV2Status(it) }
            getCarUseCase.getRV3Status().collect { it -> updateRV3Status(it) }
            getCarUseCase.getRV4Status().collect { it -> updateRV4Status(it) }
            getCarUseCase.getTrackingObj1().collect { it -> updateTrackingObj1(it) }
            getCarUseCase.getTrackingObj2().collect { it -> updateTrackingObj2(it) }
            getCarUseCase.getTrackingObj3().collect { it -> updateTrackingObj3(it) }
            getCarUseCase.getWow().collect { it -> updateWow(it) }
        }
    }

    private fun registCallback() {
        GlobalScope.launch {
            async { callbackWarning() }
            async { callbackInform() }
            async { callbackSPat() }
            async { callbackVehicleStatus() }
            async { callbackHVPos() }
            async { callbackHVMotion() }
            async { callbackRV1() }
            async { callbackRV2() }
            async { callbackRV3() }
            async { callbackRV4() }
            async { callbackTrackingObj1() }
            async { callbackTrackingObj2() }
            async { callbackTrackingObj3() }
            async { callbackWow() }
        }
    }

    private fun updateSPaT(data: V2XSPaT) {
        _spat.postValue(data.toString())
    }

    private fun updateInform(data: V2XWarnInform) {
        _inform.postValue(data.toString())
        _warning_pused.postValue(ResourceMapper().mapToWarningPushed(data.pushed))
        _id_range.postValue(ResourceMapper().mapToRange(data.range, data.type))
        _warning_distance.postValue(ResourceMapper().mapToDistance(data.range))
        _warning_text.postValue(context.resources.getString(ResourceMapper().mapToWarningText(data.text_id)))
        _id_warning_icon.postValue(ResourceMapper().mapToWarningIcon(data.icon_id))
        _id_warning_direction.postValue(ResourceMapper().mapToWarningDirection(data.direction, data.type))
        _id_warning_severity.postValue(ResourceMapper().mapToWarningSeverity(data.severity, data.type, data.direction))
        _warning_type.postValue(ResourceMapper().mapToWarningType(data.type))
        updateAudio(data.audio_id)
    }

    private fun updateWarning(data: V2XWarnInform) {
        _warning.postValue(data.toString())
        _warning_pused.postValue(ResourceMapper().mapToWarningPushed(data.pushed))
        _id_range.postValue(ResourceMapper().mapToRange(data.range, data.type))
        _warning_distance.postValue(ResourceMapper().mapToDistance(data.range))
        _warning_text.postValue(context.resources.getString(ResourceMapper().mapToWarningText(data.text_id)))
        _id_warning_icon.postValue(ResourceMapper().mapToWarningIcon(data.icon_id))
        _id_warning_direction.postValue(ResourceMapper().mapToWarningDirection(data.direction, data.type))
        _id_warning_severity.postValue(ResourceMapper().mapToWarningSeverity(data.severity, data.type, data.direction))
        _warning_type.postValue(ResourceMapper().mapToWarningType(data.type))
        updateAudio(data.audio_id)
    }
    private fun updateVehicleStatus(data: V2XVehicleStatus) {
        _vehicle.postValue(data.toString())
        _id_light_hazard.postValue(ResourceMapper().mapToLightHazard(data.light))
        _id_light_left.postValue(ResourceMapper().mapToLightLeft(data.light))
        _id_light_right.postValue(ResourceMapper().mapToLightRight(data.light))
        _id_car_light_brake.postValue(ResourceMapper().mapToLightBrake(data.light))
        _id_gear_d.postValue(ResourceMapper().mapToGearD(data.gear))
        _id_gear_p.postValue(ResourceMapper().mapToGearP(data.gear))
        _id_gear_r.postValue(ResourceMapper().mapToGearR(data.gear))
        _id_gear_n.postValue(ResourceMapper().mapToGearN(data.gear))
        _id_v2x_status_a.postValue(ResourceMapper().mapToV2XApp(data.v2xstatus))
        _id_v2x_status_g.postValue(ResourceMapper().mapToV2XGps(data.v2xstatus))
        _id_v2x_status_v.postValue(ResourceMapper().mapToV2XV2V(data.v2xstatus))
        _id_v2x_status_i.postValue(ResourceMapper().mapToV2XV2I(data.v2xstatus))
        _vehicle_speed.postValue(data.speed)
    }

    private fun updateAudio(id: Int) {
        var audio_id = ResourceMapper().mapToWarningAudio(id)
        if ( audio_id == 0 ) {
            var voice_id = ResourceMapper().mapToWarningVoice(id)
            if ( voice_id != 0 ) _warning_voice.postValue(context.resources.getString(voice_id))
        } else {
            _warning_audio.postValue(audio_id)
        }
    }

    private fun updateHVMotion(data: V2XHVMotion) { _hv_motion.postValue(data.toString()) }
    private fun updateHVPos(data: V2XHVPos) { _hv_pos.postValue(data.toString()) }
    private fun updateRV1Status(data: V2XRSUStatus) { _rv1.postValue(data.toString()) }
    private fun updateRV2Status(data: V2XRSUStatus) { _rv2.postValue(data.toString()) }
    private fun updateRV3Status(data: V2XRSUStatus) { _rv3.postValue(data.toString()) }
    private fun updateRV4Status(data: V2XRSUStatus) { _rv4.postValue(data.toString()) }
    private fun updateTrackingObj1(data: V2XTrackingObj) { _track_obj1.postValue(data.toString()) }
    private fun updateTrackingObj2(data: V2XTrackingObj) { _track_obj2.postValue(data.toString()) }
    private fun updateTrackingObj3(data: V2XTrackingObj) { _track_obj3.postValue(data.toString()) }
    private fun updateWow(data: V2XWow) { _wow.postValue(data.toString()) }

    suspend fun callbackWarning() { getCarUseCase.callbackWarning().collect { value -> updateWarning(value) } }
    suspend fun callbackInform() { getCarUseCase.callbackInform().collect { value -> updateInform(value) } }
    suspend fun callbackVehicleStatus() { getCarUseCase.callbackVehicleStatus().collect { value -> updateVehicleStatus(value) } }
    suspend fun callbackSPat() { getCarUseCase.callbackSPaT().collect { value -> updateSPaT(value) } }
    suspend fun callbackHVPos() { getCarUseCase.callbackHVPos().collect { value -> updateHVPos(value) } }
    suspend fun callbackHVMotion() { getCarUseCase.callbackHVMotion().collect { value -> updateHVMotion(value) } }
    suspend fun callbackRV1() { getCarUseCase.callbackRV1Status().collect { value -> updateRV1Status(value) } }
    suspend fun callbackRV2() { getCarUseCase.callbackRV2Status().collect { value -> updateRV2Status(value) } }
    suspend fun callbackRV3() { getCarUseCase.callbackRV3Status().collect { value -> updateRV3Status(value) } }
    suspend fun callbackRV4() { getCarUseCase.callbackRV4Status().collect { value -> updateRV4Status(value) } }
    suspend fun callbackTrackingObj1() { getCarUseCase.callbackTrackingObj1().collect { value -> updateTrackingObj1(value) } }
    suspend fun callbackTrackingObj2() { getCarUseCase.callbackTrackingObj2().collect { value -> updateTrackingObj2(value) } }
    suspend fun callbackTrackingObj3() { getCarUseCase.callbackTrackingObj3().collect { value -> updateTrackingObj3(value) } }
    suspend fun callbackWow() { getCarUseCase.callbackWow().collect { value -> updateWow(value) } }
}