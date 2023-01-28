package com.hfad.myweatherlistapp.viewmodel.list.citieslist

import com.hfad.myweatherlistapp.domain.Weather

sealed class CityListFragmentAppState {

    data class SuccessOne(val weatherData: Weather) : CityListFragmentAppState()
    data class SuccessMulti(val weatherList: List<Weather>) : CityListFragmentAppState()
    data class Error(val error: Throwable) : CityListFragmentAppState()
    object Loading : CityListFragmentAppState()
}

