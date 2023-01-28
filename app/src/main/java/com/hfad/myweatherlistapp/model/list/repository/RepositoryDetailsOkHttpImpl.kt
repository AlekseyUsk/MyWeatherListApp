package com.hfad.myweatherlistapp.model.repository

import com.hfad.myweatherlistapp.domain.Weather
import com.hfad.myweatherlistapp.domain.getDefaultCity

class RepositoryDetailsOkHttpImpl : RepositoryDetails {
    override fun getWeather(lat: Double, lon: Double): Weather {
        return Weather(getDefaultCity())
    }
}
