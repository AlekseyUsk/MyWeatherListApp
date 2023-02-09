package com.hfad.myweatherlistapp.model.repository

import com.hfad.myweatherlistapp.domain.Weather
import java.io.IOException

fun interface RepositoryLocationToOneWeather {
    fun getWeather(weather: Weather,callback: MyLargeSuperCallback)
}
// добавления погоды
fun interface RepositoryWeatherAddable{
    fun addWeather(weather: Weather)
}

// один большой калбек для всех
interface MyLargeSuperCallback{
    fun onResponse(weather: Weather)
    fun onFailure(e : IOException)
}

fun interface RepositoryCitiesList{
    fun getListCities(location: Location): List<Weather>
}

sealed class Location() {
    object Russian : Location()
    object World : Location()
}