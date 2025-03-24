package com.szym.mie.ecoclock

interface Atom<V> {
    fun update()
    fun read() : V
}