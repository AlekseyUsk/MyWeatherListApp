package com.hfad.myweatherlistapp.model.repository

import com.hfad.myweatherlistapp.domain.Weather

fun interface RepositoryDetails {
    fun getWeather(lat: Double, lon: Double): Weather
}

fun interface RepositoryCitiesList{
    fun getListCities(location: Location): List<Weather>
}

sealed class Location {

    object Russian : Location()
    object World : Location()
}