package com.hfad.myweatherlistapp.view

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import com.hfad.myweatherlistapp.R
import com.hfad.myweatherlistapp.utils.SP_BD_IS_RUSSIA
import com.hfad.myweatherlistapp.utils.SP_KEY_IS_RUSSIA

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, CitiesListFragment.newInstance())
                .commit()
        }

        registerReceiver(object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                Log.d("@@@", "onReceive(MainActivity)onReceive()${Thread.currentThread()}")
            }
        }, IntentFilter("answer"))
        
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_switching, menu)
        return true
    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        //val sp = getSharedPreferences("SP_ACTIVITY",Context.MODE_PRIVATE)
//        when (item.itemId) {
//            R.id.retrofit -> {
//
//            }
//            R.id.okHttp3 ->{
//
//            }
//        }
//        return true
//    }

}


