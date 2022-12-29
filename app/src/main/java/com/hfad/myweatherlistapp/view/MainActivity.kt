package com.hfad.myweatherlistapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hfad.myweatherlistapp.R

    class MainActivity : AppCompatActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)

            if (savedInstanceState == null) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, WeatherListFragment.newInstance())
                    .commit()
            }
        }
    }


