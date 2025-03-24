package com.szym.mie.ecoclock

interface Component<V> : Atom<V> {
    fun render(viewport: Viewport, position: Pair<Float, Float>)
}