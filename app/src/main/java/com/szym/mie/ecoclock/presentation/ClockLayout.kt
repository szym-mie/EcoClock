package com.szym.mie.ecoclock.presentation

import android.annotation.SuppressLint
import android.content.Context
import androidx.wear.widget.SwipeDismissFrameLayout

@SuppressLint("ClickableViewAccessibility")
class ClockLayout(context: Context) : SwipeDismissFrameLayout(context) {
    init {
        isSwipeable = true
        setOnTouchListener { _, _ -> true }
    }
}