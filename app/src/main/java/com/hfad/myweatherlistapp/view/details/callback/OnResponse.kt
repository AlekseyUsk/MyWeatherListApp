package com.hfad.myweatherlistapp.view.details.callback

import com.hfad.myweatherlistapp.model.list.WeatherDTO

interface OnResponse {
    fun onResponse(weather: WeatherDTO) : WeatherDTO {
        return weather
    }
}