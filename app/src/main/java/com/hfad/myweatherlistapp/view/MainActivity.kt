package com.hfad.myweatherlistapp.view

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.hfad.myweatherlistapp.R
import com.hfad.myweatherlistapp.homeTrening.TestService

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, WeatherListFragment.newInstance())
                .commit()
        }
            //Дома тренировался
        startService(Intent(this, TestService::class.java).apply {
        })

        registerReceiver(object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                Log.d("@@@", "onReceive(MainActivity)onReceive()${Thread.currentThread()}")
            }
        }, IntentFilter("answer"))
    }
}


