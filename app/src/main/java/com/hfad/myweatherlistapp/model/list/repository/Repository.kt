package com.hfad.myweatherlistapp.model.repository

import com.hfad.myweatherlistapp.domain.Weather
import com.hfad.myweatherlistapp.model.list.WeatherDTO
import java.io.IOException

fun interface RepositoryDetails {
    fun getWeather(lat: Double, lon: Double,callback: MyLargeSuperCallback)
}
// один большой калбек для всех
interface MyLargeSuperCallback{
    fun onResponse(weatherDTO: WeatherDTO)
    fun onFailure(e : IOException)
}

fun interface RepositoryCitiesList{
    fun getListCities(location: Location): List<Weather>
}

sealed class Location {

    object Russian : Location()
    object World : Location()
}