package com.humaxdigital.automotive.v2xpoc.presentation.v2x

import android.content.Context
import android.media.AudioManager
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.humaxdigital.automotive.v2xpoc.R
import com.humaxdigital.automotive.v2xpoc.databinding.FragmentEbwBinding
import com.humaxdigital.automotive.v2xpoc.presentation.entities.V2XPUSHED
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class EBWFragment : Fragment() {
    private val TAG = this.javaClass.name
    private val vm: MainViewModel by sharedViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bind = (DataBindingUtil.inflate(inflater, R.layout.fragment_ebw,
            container, false) as FragmentEbwBinding)
        bind.vm = vm
        bind.setLifecycleOwner(activity)
        vm.warning_audio.observe(this, Observer {
            when(vm.warning_pused.value) {
                V2XPUSHED.ADD -> {
                    Log.d(TAG, it)
                    if ( it.equals("beep") ) {
                        //ToneGenerator(AudioManager.STREAM_MUSIC, 100).startTone(ToneGenerator.TONE_CDMA_ABBR_ALERT, 500)
                        var audio = context!!.getSystemService(Context.AUDIO_SERVICE) as AudioManager
                        audio.playSoundEffect(AudioManager.FX_KEY_CLICK)
                    }
                    else vm.tts.speak(it, TextToSpeech.QUEUE_ADD, null, null)
                }
                else -> {}
            }
        })
        return bind.root
    }
}