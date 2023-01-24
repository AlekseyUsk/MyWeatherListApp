package com.hfad.myweatherlistapp.homeTrening

import android.app.IntentService
import android.content.Intent
import android.util.Log

class TestService : IntentService("тестсервис") {
    override fun onHandleIntent(intent: Intent?) {
        intent?.let {
            Log.d("@@@","TestService onHandleIntent()${Thread.currentThread()}")
            sendBroadcast(Intent().apply {
                action = "answer"
            })
        }
    }
}