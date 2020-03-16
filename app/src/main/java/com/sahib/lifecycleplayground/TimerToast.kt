package com.sahib.lifecycleplayground

import android.app.Application
import android.os.CountDownTimer
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent

//We have made the TimerToast class lifecycle aware here
//So it shows the toast in the activity only when the activity is in visible
class TimerToast(application: Application, lifecycleOwner: LifecycleOwner) : LifecycleObserver {

    private var started = false
    private val lifecycle: Lifecycle = lifecycleOwner.lifecycle

    init {
        lifecycle.addObserver(this)
    }

    private val timer: CountDownTimer = object : CountDownTimer(60000, 3000) {
        //Both these methods run on the background thread
        override fun onFinish() {
            if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED))
                Toast.makeText(application, "Finish", Toast.LENGTH_SHORT).show()
        }

        override fun onTick(millisUntilFinished: Long) {
            if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED))
                Toast.makeText(application, "$millisUntilFinished", Toast.LENGTH_SHORT).show()
        }
    }

    //Start and stop method here run on the main thread
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun start() {
        if (!started) {
            started = true
            timer.start()
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun stop() {
        timer.cancel()
    }
}