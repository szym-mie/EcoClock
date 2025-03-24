package com.szym.mie.ecoclock

import android.os.Handler
import android.view.View
import androidx.activity.ComponentActivity
import androidx.wear.ambient.AmbientLifecycleObserver

class UpdateController(val view: Atom<View>, val mainDelay: Long) {
    private var ambientObserver: AmbientLifecycleObserver? = null
    private var mainHandler: Handler? = null
    private var mainHandlerRunnable: Runnable? = null
    private var attachedActivity: ComponentActivity? = null
    var inAmbient = false

    init {
        mainHandlerRunnable = Runnable {
            mainHandler?.let {
                view.update()
                if (!inAmbient) it.postDelayed(mainHandlerRunnable!!, mainDelay)
            }
        }
    }

    fun attach(activity: ComponentActivity) {
        ambientObserver = AmbientLifecycleObserver(activity, Ambient())
        activity.lifecycle.addObserver(ambientObserver!!)
        mainHandler = Handler(activity.mainLooper)
        mainHandlerRunnable?.run()
        attachedActivity = activity
    }

    fun detach() {
        ambientObserver?.let {
            attachedActivity?.lifecycle?.removeObserver(it)
        }
        mainHandler = null
    }

    inner class Ambient : AmbientLifecycleObserver.AmbientLifecycleCallback {
        override fun onEnterAmbient(ambientDetails: AmbientLifecycleObserver.AmbientDetails) {
            super.onEnterAmbient(ambientDetails)
            inAmbient = true
            println("enter ambient")
        }

        override fun onUpdateAmbient() {
            super.onUpdateAmbient()
            view.update()
        }

        override fun onExitAmbient() {
            super.onExitAmbient()
            inAmbient = false
            mainHandlerRunnable?.run()
            println("exit ambient")
        }
    }
}