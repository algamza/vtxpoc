package com.humaxdigital.automotive.v2xpoc.presentation.v2x

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.humaxdigital.automotive.v2xpoc.R
import com.humaxdigital.automotive.v2xpoc.presentation.entities.V2XPUSHED
import com.humaxdigital.automotive.v2xpoc.presentation.entities.V2XTYPE
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private val TAG = this.javaClass.name
    private val vm: MainViewModel by viewModel()
    private val flog = LogFragment()
    private var is_show = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().add(R.id.fragment_main, MainFragment()).commit()
        supportFragmentManager.beginTransaction().add(R.id.fragment_side, SideFragment()).commit()

        // test
        supportFragmentManager.beginTransaction().add(R.id.fragment_log, flog).commit()
        fragment_log.setOnLongClickListener() {
            when(is_show) {
                true -> {
                    supportFragmentManager.beginTransaction().hide(flog).commit()
                    is_show = false
                }
                false -> {
                    supportFragmentManager.beginTransaction().show(flog).commit()
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
                            .replace(R.id.fragment_main, mapToFragment(vm.warning_type.value!!) as Fragment)
                            .commit()
                    }
                    V2XPUSHED.DELETE -> {
                        supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.fragment_main, MainFragment())
                            .commit()
                    }
                    V2XPUSHED.UPDATE -> {}
                    V2XPUSHED.NONE -> {}
                }
            } catch (e: Exception) {
                Log.d(TAG, e.toString())
            }
        })
    }

    private fun mapToFragment(type: V2XTYPE) = when(type) {
        V2XTYPE.EBW -> EBWFragment()
        V2XTYPE.EVW -> EVWFragment()
        V2XTYPE.NONE -> {}
        V2XTYPE.HB -> {}
        V2XTYPE.FCW -> {}
        V2XTYPE.ICW -> {}
        V2XTYPE.LTA -> {}
        V2XTYPE.BSW_LCW -> {}
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
        else -> MainFragment()
    }
}
