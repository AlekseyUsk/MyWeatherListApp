package com.hfad.myweatherlistapp.repository

import com.hfad.myweatherlistapp.domain.Weather
import com.hfad.myweatherlistapp.domain.getDefaultCity

class RepositoryRemoteImpl : RepositoryMultipleWeatherQuery, RepositoryOneWeather {
    override fun getListWeather(location: Location): List<Weather> {
        return listOf(Weather())
    }

    override fun getWeather(lat: Double, lon: Double): Weather {
       return Weather(getDefaultCity())//заглушка
    }


}