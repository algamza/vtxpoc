package com.humaxdigital.automotive.v2xpoc.presentation.v2x

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.humaxdigital.automotive.v2xpoc.R
import com.humaxdigital.automotive.v2xpoc.databinding.ActivityTestMainBinding
import org.koin.android.viewmodel.ext.android.viewModel

class TestMain : AppCompatActivity() {
    private val testmodel: TestMainViewModel by viewModel()
    private val bind by lazy { DataBindingUtil.setContentView<ActivityTestMainBinding>(this, R.layout.activity_test_main) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind.test = testmodel
        bind.setLifecycleOwner(this)
    }
}
