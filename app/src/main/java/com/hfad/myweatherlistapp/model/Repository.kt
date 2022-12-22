package com.hfad.myweatherlistapp.model

import com.hfad.myweatherlistapp.domain.Weather

interface Repository {

    fun getListWeather(): List<Weather>
    fun getWeather(lat: Double, lon: Double): Weather
}