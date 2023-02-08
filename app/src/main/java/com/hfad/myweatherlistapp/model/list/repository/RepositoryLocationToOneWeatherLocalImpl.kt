package com.hfad.myweatherlistapp.model.repository

import com.hfad.myweatherlistapp.domain.Weather
import com.hfad.myweatherlistapp.domain.getRussianCities
import com.hfad.myweatherlistapp.domain.getWorldCities

class RepositoryLocationToOneWeatherLocalImpl : RepositoryLocationToOneWeather {

    override fun getWeather(weather: Weather, callback: MyLargeSuperCallback) {
        val list = getWorldCities().toMutableList() //сделал мутабельный список
        list.addAll(getRussianCities()) // добавил города россии в этот список
        //после того как скомпоновал список
        val response = list.filter { it.city.lat == weather.city.lat && it.city.lon == weather.city.lon }
        callback.onResponse((response.first()))
    }
}
