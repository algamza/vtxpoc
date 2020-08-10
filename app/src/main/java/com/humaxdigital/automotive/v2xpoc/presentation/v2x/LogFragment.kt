package com.humaxdigital.automotive.v2xpoc.presentation.v2x

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.humaxdigital.automotive.v2xpoc.R
import kotlinx.android.synthetic.main.fragment_log.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

class LogFragment : Fragment() {
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
        vm.warning.observe(this, Observer {
            text_log.append(it+"\n")
            //log_scroll.fullScroll(View.FOCUS_DOWN)
        })
        vm.inform.observe(this, Observer {
            text_log.append(it+"\n")
            //log_scroll.fullScroll(View.FOCUS_DOWN)
        })
        vm.vehicle.observe(this, Observer {
            text_log.append(it+"\n")
            //log_scroll.fullScroll(View.FOCUS_DOWN)
        })
        vm.spat.observe(this, Observer {
            text_log.append(it+"\n")
            //log_scroll.fullScroll(View.FOCUS_DOWN)
        })
        vm.hv_pos.observe(this, Observer {
            text_log.append(it+"\n")
            //log_scroll.fullScroll(View.FOCUS_DOWN)
        })
        vm.hv_motion.observe(this, Observer {
            text_log.append(it+"\n")
            //log_scroll.fullScroll(View.FOCUS_DOWN)
        })
        vm.rv1.observe(this, Observer {
            text_log.append(it+"\n")
            //log_scroll.fullScroll(View.FOCUS_DOWN)
        })
        vm.rv2.observe(this, Observer {
            text_log.append(it+"\n")
            //log_scroll.fullScroll(View.FOCUS_DOWN)
        })
        vm.rv3.observe(this, Observer {
            text_log.append(it+"\n")
            //log_scroll.fullScroll(View.FOCUS_DOWN)
        })
        vm.rv4.observe(this, Observer {
            text_log.append(it+"\n")
            //log_scroll.fullScroll(View.FOCUS_DOWN)
        })
        return inflater.inflate(R.layout.fragment_log, null)
    }
}