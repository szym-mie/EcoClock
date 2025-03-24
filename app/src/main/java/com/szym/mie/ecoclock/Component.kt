package com.szym.mie.ecoclock

import android.graphics.Canvas

interface Component<V> : Atom<V> {
    fun render(canvas: Canvas, position: Pair<Float, Float>)
}