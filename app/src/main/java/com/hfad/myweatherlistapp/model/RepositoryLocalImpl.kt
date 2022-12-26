package com.hfad.myweatherlistapp.model

import com.hfad.myweatherlistapp.domain.Weather
import com.hfad.myweatherlistapp.domain.getRussianCities
import com.hfad.myweatherlistapp.domain.getWorldCities

class RepositoryLocalImpl : RepositoryOneWeather,RepositoryMultipleWeatherQuery {
    override fun getListWeather(location: Location): List<Weather> {
        return when (location) {
            Location.Russian -> {
                getRussianCities()
            }
            Location.World -> {
                getWorldCities()
            }
        }
    }

    override fun getWeather(lat: Double, lon: Double): Weather {
        return Weather()
    }
}