package com.szym.mie.ecoclock.presentation

import android.content.Context
import android.graphics.Canvas
import android.view.View
import com.szym.mie.ecoclock.Atom
import com.szym.mie.ecoclock.UpdateController
import com.szym.mie.ecoclock.Viewport
import com.szym.mie.ecoclock.components.BatteryComponent
import com.szym.mie.ecoclock.components.TimeComponent

class ClockView(context: Context) : View(context), Atom<View> {
    // start update controller at 1000ms interval and attach it
    val updateController = UpdateController(this, 1000)
    private val viewport = Viewport(null)
    private val timeComponent = TimeComponent()
    private val batteryComponent = BatteryComponent(context)

    override fun update() {
        timeComponent.update()
        batteryComponent.update()
        invalidate()
    }

    override fun read() = this

    private var sizeX = 0f
    private var sizeY = 0f
    private var centerX = 0f
    private var centerY = 0f
    private var timeComponentPosition = Pair(0f, 0f)
    private var batteryComponentPosition = Pair(0f, 0f)

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        sizeX = w.toFloat()
        sizeY = h.toFloat()
        centerX = sizeX * 0.5f
        centerY = sizeY * 0.5f

        timeComponentPosition = Pair(centerX, centerY + 30f)
        batteryComponentPosition = Pair(centerX, centerY)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        viewport.canvas = canvas
        viewport.inAmbient = updateController.inAmbient
        viewport.also {
            batteryComponent.render(it, batteryComponentPosition)
            timeComponent.render(it, timeComponentPosition)
        }
    }
}