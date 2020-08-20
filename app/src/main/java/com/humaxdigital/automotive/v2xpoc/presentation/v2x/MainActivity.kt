package com.humaxdigital.automotive.v2xpoc.presentation.v2x

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.humaxdigital.automotive.v2xpoc.R
import com.humaxdigital.automotive.v2xpoc.presentation.entities.V2XPUSHED
import com.humaxdigital.automotive.v2xpoc.presentation.entities.V2XTYPE
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_log.*
import org.koin.android.ext.android.get
import org.koin.androidx.fragment.android.setupKoinFragmentFactory
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private val TAG = this.javaClass.name
    private val vm: MainViewModel by viewModel()
    private var is_show = true

    override fun onCreate(savedInstanceState: Bundle?) {
        setupKoinFragmentFactory()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_main, MainFragment::class.java, null, null).commit()
        supportFragmentManager
            .beginTransaction().add(R.id.fragment_side, SideFragment::class.java, null, null).commit()

        // test
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_log, LogFragment::class.java, null, null).commit()

        fragment_log.setOnLongClickListener() {
            when(is_show) {
                true -> {
                    text_log.visibility = View.GONE
                    is_show = false
                }
                false -> {
                    text_log.visibility = View.VISIBLE
                    is_show = true
                }
            }
            true
        }

        vm.warning_pused.observe(this, Observer {
            try {
                when(it) {
                    V2XPUSHED.ADD -> {
                        supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.fragment_main, mapToFragment(vm.warning_type.value!!), null, null)
                            .commit()
                    }
                    V2XPUSHED.DELETE -> {
                        supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.fragment_main, MainFragment::class.java, null, null)
                            .commit()
                    }
                    V2XPUSHED.UPDATE -> {}
                    V2XPUSHED.NONE -> {}
                    else -> {}
                }
            } catch (e: Exception) {
                Log.d(TAG, e.toString())
            }
        })
    }

    private fun mapToFragment(type: V2XTYPE) = when(type) {
        V2XTYPE.EBW -> EBWFragment::class.java
        V2XTYPE.EVW -> EVWFragment::class.java
        V2XTYPE.ICW -> ICWFragment::class.java
        V2XTYPE.LTA -> LTAFragemnt::class.java
        V2XTYPE.FCW -> FCWFragment::class.java
        V2XTYPE.BSW_LCW -> BSWFragment::class.java
        /*
        V2XTYPE.NONE -> {}
        V2XTYPE.HB -> {}
        V2XTYPE.DNPW -> {}
        V2XTYPE.AVW -> {}
        V2XTYPE.CLW -> {}
        V2XTYPE.HLW -> {}
        V2XTYPE.SLW -> {}
        V2XTYPE.RLVW -> {}
        V2XTYPE.VRUCW  -> {}
        V2XTYPE.GLOSA -> {}
        V2XTYPE.IVS  -> {}
        V2XTYPE.TJW -> {}
         */
        else -> MainFragment::class.java
    }
}
