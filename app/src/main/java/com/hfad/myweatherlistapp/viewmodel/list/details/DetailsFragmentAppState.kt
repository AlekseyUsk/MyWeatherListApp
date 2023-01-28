package com.hfad.myweatherlistapp.viewmodel.list.details

import com.hfad.myweatherlistapp.domain.Weather

sealed class DetailsFragmentAppState {

    data class Success(val weatherData: Weather) : DetailsFragmentAppState()
    data class Error(val error: Throwable) : DetailsFragmentAppState()
    object Loading : DetailsFragmentAppState()
}

