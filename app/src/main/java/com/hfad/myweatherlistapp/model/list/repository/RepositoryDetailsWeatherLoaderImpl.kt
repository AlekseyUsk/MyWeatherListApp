package com.hfad.myweatherlistapp.model.list.repository

import com.hfad.myweatherlistapp.domain.Weather
import com.hfad.myweatherlistapp.domain.getDefaultCity
import com.hfad.myweatherlistapp.model.repository.RepositoryDetails

class RepositoryDetailsWeatherLoaderImpl : RepositoryDetails {
    override fun getWeather(lat: Double, lon: Double): Weather {
        return Weather(getDefaultCity())
    }
}