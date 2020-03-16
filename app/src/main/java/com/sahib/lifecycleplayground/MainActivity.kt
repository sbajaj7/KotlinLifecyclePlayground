package com.sahib.lifecycleplayground

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

class MainActivity : AppCompatActivity() {

    //private lateinit var timerToast: TimerToast
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //timerToast = TimerToast(application, this)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.timerData.observe(this, Observer {
            Toast.makeText(application, it, Toast.LENGTH_SHORT).show()
        })

        viewModel.start()
    }
}
