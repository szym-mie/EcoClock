package com.szym.mie.ecoclock.presentation

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.szym.mie.ecoclock.R
import com.szym.mie.ecoclock.Resources

class MainActivity : FragmentActivity() {
    private lateinit var view: ClockView
    private lateinit var layout: ClockLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Resources.load(resources)
        setTheme(R.style.MainActivityTheme_Default)
        view = ClockView(applicationContext)
        view.updateController.attach(this)
        layout = ClockLayout(applicationContext)
        layout.addView(view)
        setContentView(layout)
    }

    override fun onDestroy() {
        super.onDestroy()
        view.updateController.detach()
    }
}