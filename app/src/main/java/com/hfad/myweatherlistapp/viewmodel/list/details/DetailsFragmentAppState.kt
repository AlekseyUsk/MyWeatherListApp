package com.hfad.myweatherlistapp.viewmodel.list.details

import com.hfad.myweatherlistapp.model.list.WeatherDTO

sealed class DetailsFragmentAppState {

    data class Success(val weatherData: WeatherDTO) : DetailsFragmentAppState()
    data class Error(val error: Throwable) : DetailsFragmentAppState()
    object Loading : DetailsFragmentAppState()
}

