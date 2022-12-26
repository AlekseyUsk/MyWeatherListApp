package com.hfad.myweatherlistapp.model

import com.hfad.myweatherlistapp.domain.Weather
import com.hfad.myweatherlistapp.domain.getDefaultCity
import com.hfad.myweatherlistapp.viewmodel.AppState

class RepositoryRemoteImpl : RepositoryMultipleWeatherQuery, RepositoryOneWeather {
    override fun getListWeather(location: Location): List<Weather> {
        return listOf(Weather())
    }

    override fun getWeather(lat: Double, lon: Double): Weather {
       return Weather(getDefaultCity())//заглушка
    }


}