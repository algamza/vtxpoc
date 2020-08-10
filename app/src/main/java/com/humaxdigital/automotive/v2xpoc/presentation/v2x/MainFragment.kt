package com.humaxdigital.automotive.v2xpoc.presentation.v2x

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.humaxdigital.automotive.v2xpoc.R
import com.humaxdigital.automotive.v2xpoc.databinding.FragmentMainBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class MainFragment : Fragment() {
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
        val bind = (DataBindingUtil.inflate(inflater, R.layout.fragment_main,
            container, false) as FragmentMainBinding)
        bind.vm = vm
        bind.setLifecycleOwner(activity)
        return bind.root
    }
}