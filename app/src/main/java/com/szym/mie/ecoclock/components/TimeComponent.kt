package com.szym.mie.ecoclock.components

import android.graphics.Canvas
import android.graphics.Paint
import androidx.core.graphics.toColorInt
import com.szym.mie.ecoclock.Component
import java.time.ZonedDateTime

class TimeComponent : Component<ZonedDateTime> {
    private val timeTextPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = "#d0d0d0".toColorInt()
        textAlign = Paint.Align.CENTER
        textSize = 80f
    }

    private lateinit var time: ZonedDateTime
    init {
        update()
    }

    override fun update() {
        time = ZonedDateTime.now()
    }

    override fun read() = time

    override fun render(canvas: Canvas, position: Pair<Float, Float>) {
        val (x, y) = position
        val zonedTime = read()
        val hour = zonedTime.hour.toString().padStart(2, '0')
        val minute = zonedTime.minute.toString().padStart(2, '0')
        canvas.apply {
            drawText(hour, x, y - 40f, timeTextPaint)
            drawText(minute, x, y + 40f, timeTextPaint)
        }
    }
}