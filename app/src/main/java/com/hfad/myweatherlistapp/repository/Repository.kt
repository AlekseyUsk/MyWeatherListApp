package com.hfad.myweatherlistapp.repository

import com.hfad.myweatherlistapp.domain.Weather

fun interface RepositoryOneWeather {
    fun getWeather(lat: Double, lon: Double): Weather
}

fun interface RepositoryMultipleWeatherQuery {
    fun getListWeather(location: Location): List<Weather>
}

sealed class Location {

    object Russian : Location()
    object World : Location()
}