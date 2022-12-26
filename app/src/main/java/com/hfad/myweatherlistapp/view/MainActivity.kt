package com.hfad.myweatherlistapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hfad.myweatherlistapp.R
import com.hfad.myweatherlistapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, WeatherListFragment.newInstance())
                .commit()
        }
    }
}