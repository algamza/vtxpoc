package com.humaxdigital.automotive.v2xpoc.presentation.v2x

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.humaxdigital.automotive.v2xpoc.R
import com.humaxdigital.automotive.v2xpoc.databinding.FragmentIcwRBinding
import com.humaxdigital.automotive.v2xpoc.presentation.entities.V2XPUSHED
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ICWRFragment : Fragment() {
    private val TAG = this.javaClass.name
    private val vm: MainViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bind = (DataBindingUtil.inflate(inflater, R.layout.fragment_icw_r,
            container, false) as FragmentIcwRBinding)
        bind.vm = vm
        bind.setLifecycleOwner(activity)
        vm.warning_voice.observe(this, Observer {
            when(vm.warning_pused.value) {
                V2XPUSHED.ADD -> {
                    vm.tts.speak(it, TextToSpeech.QUEUE_ADD, null, null)
                }
                else -> {}
            }
        })
        return bind.root
    }
}