package com.szym.mie.ecoclock.components

import android.content.Context
import android.content.Context.BATTERY_SERVICE
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.BatteryManager
import android.os.BatteryManager.BATTERY_PROPERTY_CAPACITY
import com.szym.mie.ecoclock.Component
import androidx.core.graphics.toColorInt

class BatteryComponent(context: Context) : Component<Int> {
    private val batteryGoodLightPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = "#60f060".toColorInt()
        textAlign = Paint.Align.RIGHT
        textSize = 20f
    }

    private val batteryLowLightPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = "#f06040".toColorInt()
        textAlign = Paint.Align.RIGHT
        textSize = 20f
    }

    private val batteryGoodDarkPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = "#104020".toColorInt()
    }

    private val batteryLowDarkPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = "#402010".toColorInt()
    }


    private val blackPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.BLACK
    }

    private var percentage = 0
    private val batteryManager: BatteryManager =
        context.getSystemService(BATTERY_SERVICE) as BatteryManager
    init {
        update()
    }

    override fun update() {
        percentage = batteryManager.getIntProperty(BATTERY_PROPERTY_CAPACITY)
    }

    override fun read() = percentage

    override fun render(canvas: Canvas, position: Pair<Float, Float>) {
        val (x, y) = position
        val battery = read()
        val lightPaint = if (battery > 35) batteryGoodLightPaint else batteryLowLightPaint
        val darkPaint = if (battery > 35) batteryGoodDarkPaint else batteryLowDarkPaint
        val sweep = battery / 2

        canvas.apply {
            drawArc(x - 120, y - 120, x + 120, y + 120, 160f, sweep.toFloat(), true, lightPaint)
            drawArc(x - 110, y - 110, x + 110, y + 110, 160f, 50f, true, darkPaint)
            drawArc(x - 108, y - 108, x + 108, y + 108, 120f, 120f, true, blackPaint)
            drawText("$battery%", x - 70, y + 70, lightPaint)
        }
    }
}