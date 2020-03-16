package com.sahib.lifecycleplayground

import android.os.CountDownTimer
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    val timerData = MutableLiveData<String>()

    private val timer: CountDownTimer = object : CountDownTimer(60000, 3000) {
        //Both these methods run on the background thread
        override fun onFinish() {
            timerData.postValue("Finish")
        }

        override fun onTick(millisUntilFinished: Long) {
            timerData.postValue("$millisUntilFinished")
        }
    }

    fun start() {
        timer.start()
    }

    override fun onCleared() {
        super.onCleared()
        timer.cancel()
    }
}