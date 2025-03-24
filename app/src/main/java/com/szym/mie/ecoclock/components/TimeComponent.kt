package com.szym.mie.ecoclock.components

import android.graphics.Bitmap
import android.graphics.Paint
import android.graphics.RectF
import androidx.core.graphics.toColorInt
import com.szym.mie.ecoclock.Component
import com.szym.mie.ecoclock.Resources
import com.szym.mie.ecoclock.Viewport
import java.time.ZonedDateTime

class TimeComponent() : Component<ZonedDateTime> {
    private val fullTextPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = "#d0d0d0".toColorInt()
        typeface = Resources.overpassTypeface
        textAlign = Paint.Align.CENTER
        textSize = 80f
    }

    private val dimTextPaint = Paint(0).apply {
        color = "#606060".toColorInt()
        typeface = Resources.overpassTypeface
        textAlign = Paint.Align.CENTER
        textSize = 80f
    }

    private lateinit var time: ZonedDateTime
    init {
        update()
    }

    private fun drawTime(
        viewport: Viewport,
        time: String,
        x: Float, y: Float,
        paint: Paint
    ) {
        val t0 = time[0].toString()
        val t1 = time[1].toString()
        viewport.canvas?.apply {
            drawText(t0, x - 22, y, paint)
            drawText(t1, x + 22, y, paint)
        }
    }

    private fun drawIcon(
        viewport: Viewport,
        bitmap: Bitmap,
        x: Float, y: Float,
        paint: Paint
    ) {
        val wh = 16f
        val hh = 16f
        val iconPosition = RectF(x - wh, y - hh, x + wh, y + hh)
        viewport.canvas?.drawBitmap(bitmap, null, iconPosition, paint)
    }

    override fun update() {
        time = ZonedDateTime.now()
    }

    override fun read() = time

    override fun render(viewport: Viewport, position: Pair<Float, Float>) {
        val (x, y) = position
        val zonedTime = read()
        val hour = zonedTime.hour.toString().padStart(2, '0')
        val minute = zonedTime.minute.toString().padStart(2, '0')
        val textPaint = if (!viewport.inAmbient) fullTextPaint else dimTextPaint
        val icon =
            if (!viewport.inAmbient) Resources.wakeupIconBitmap
            else Resources.ambientIconBitmap

        viewport.also {
            drawTime(it, hour, x, y - 40f, textPaint)
            drawTime(it, minute, x, y + 40f, textPaint)
            drawIcon(it, icon, x, y + 80f, textPaint)
        }
    }
}