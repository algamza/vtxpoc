package com.humaxdigital.automotive.v2xpoc.presentation.v2x

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import com.humaxdigital.automotive.v2xpoc.R
import com.humaxdigital.automotive.v2xpoc.presentation.entities.V2XPUSHED
import com.humaxdigital.automotive.v2xpoc.presentation.entities.V2XTYPE
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_log.*
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
        V2XTYPE.EVW -> {
            when(vm.id_warning_icon.value) {
                110, 113 -> EVWCFragment::class.java
                111, 114 -> EVWLFragment::class.java
                112, 115 -> EVWRFragment::class.java
                else -> EVWCFragment::class.java
            }
        }
        V2XTYPE.ICW -> {
            when(vm.id_warning_icon.value) {
                20, 21, 22 -> ICWLFragment::class.java
                23, 24, 25 -> ICWRFragment::class.java
                else -> ICWLFragment::class.java
            }
        }
        V2XTYPE.LTA -> LTAFragemnt::class.java
        V2XTYPE.FCW -> FCWFragment::class.java
        V2XTYPE.BSW_LCW -> BSWFragment::class.java
        V2XTYPE.AVW -> AVWFragment::class.java
        V2XTYPE.HLW -> {
            when(vm.id_warning_icon.value) {
                80 -> RWWAFragment::class.java
                81, 82 -> RWWLRFragment::class.java
                else -> RWWAFragment::class.java
            }
        }
        /*
        V2XTYPE.NONE -> {}
        V2XTYPE.HB -> {}
        V2XTYPE.DNPW -> {}
        V2XTYPE.CLW -> {}
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
