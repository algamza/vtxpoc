package com.humaxdigital.automotive.v2xpoc.presentation.v2x

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.humaxdigital.automotive.v2xpoc.R
import kotlinx.android.synthetic.main.fragment_log.*
import kotlinx.android.synthetic.main.fragment_log.view.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

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
            updateScroll()
        })
        vm.inform.observe(this, Observer {
            text_log.append(it+"\n")
            updateScroll()
        })
        vm.vehicle.observe(this, Observer {
            text_log.append(it+"\n")
            updateScroll()
        })
        vm.spat.observe(this, Observer {
            text_log.append(it+"\n")
            updateScroll()
        })
        vm.hv_pos.observe(this, Observer {
            text_log.append(it+"\n")
            updateScroll()
        })
        vm.hv_motion.observe(this, Observer {
            text_log.append(it+"\n")
            updateScroll()
        })
        vm.rv1.observe(this, Observer {
            text_log.append(it+"\n")
            updateScroll()
        })
        vm.rv2.observe(this, Observer {
            text_log.append(it+"\n")
            updateScroll()
        })
        vm.rv3.observe(this, Observer {
            text_log.append(it+"\n")
            updateScroll()
        })
        vm.rv4.observe(this, Observer {
            text_log.append(it+"\n")
            updateScroll()
        })
        vm.track_obj1.observe(this, Observer {
            text_log.append(it+"\n")
            updateScroll()
        })
        vm.track_obj2.observe(this, Observer {
            text_log.append(it+"\n")
            updateScroll()
        })
        vm.track_obj3.observe(this, Observer {
            text_log.append(it+"\n")
            updateScroll()
        })
        vm.wow.observe(this, Observer {
            text_log.append(it+"\n")
            updateScroll()
        })

        return inflater.inflate(R.layout.fragment_log, null)
    }

    private fun updateScroll() {
        var linetop = text_log.layout.getLineTop(text_log.lineCount)
        var scrolly = linetop - text_log.height
        if ( scrolly > 0 ) text_log.scrollTo(0, scrolly)
        else text_log.text_log.scrollTo(0, 0)
    }

    override fun onStart() {
        super.onStart()
        text_log.movementMethod = ScrollingMovementMethod()
    }
}