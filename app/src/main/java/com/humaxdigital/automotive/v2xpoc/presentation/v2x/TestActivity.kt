package com.humaxdigital.automotive.v2xpoc.presentation.v2x

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.humaxdigital.automotive.v2xpoc.R
import com.humaxdigital.automotive.v2xpoc.databinding.ActivityTestBinding
import org.koin.android.viewmodel.ext.android.viewModel

class TestActivity : AppCompatActivity() {

    private val testmodel: TestViewModel by viewModel()
    private val bind by lazy { DataBindingUtil.setContentView<ActivityTestBinding>(this, R.layout.activity_test) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind.test = testmodel
        bind.setLifecycleOwner(this)
    }
}
