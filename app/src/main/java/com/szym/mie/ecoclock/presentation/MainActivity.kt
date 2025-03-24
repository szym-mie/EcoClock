package com.szym.mie.ecoclock.presentation

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.szym.mie.ecoclock.R
import com.szym.mie.ecoclock.UpdateController

class MainActivity : FragmentActivity() {
    private lateinit var updateController: UpdateController
    private lateinit var view: ClockView
    private lateinit var layout: ClockLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setTheme(R.style.MainActivityTheme_Default)
        view = ClockView(applicationContext)
        layout = ClockLayout(applicationContext)
        layout.addView(view)
        setContentView(layout)

        // start update controller at 1000ms interval and attach it
        updateController = UpdateController(view, 1000)
        updateController.attach(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        updateController.detach()
    }
}