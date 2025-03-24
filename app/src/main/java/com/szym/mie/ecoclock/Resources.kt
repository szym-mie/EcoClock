package com.szym.mie.ecoclock

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Typeface

object Resources {
    lateinit var overpassTypeface: Typeface
    lateinit var ambientIconBitmap: Bitmap
    lateinit var wakeupIconBitmap: Bitmap

    private fun loadFont(resources: Resources, resourceId: Int) =
        resources.getFont(resourceId)

    private fun loadBitmap(resources: Resources, resourceId: Int) =
        BitmapFactory.decodeResource(resources, resourceId)

    fun load(resources: Resources) {
        overpassTypeface = loadFont(resources, R.font.overpass_medium)
        ambientIconBitmap = loadBitmap(resources, R.drawable.ambient_icon)
        wakeupIconBitmap = loadBitmap(resources, R.drawable.wakeup_icon)
    }
}