package com.hfad.myweatherlistapp.model.repository

import com.hfad.myweatherlistapp.domain.Weather
import com.hfad.myweatherlistapp.domain.getRussianCities
import com.hfad.myweatherlistapp.domain.getWorldCities

class RepositoryCitiesListImpl : RepositoryCitiesList {
    override fun getListCities(location: Location): List<Weather> {
        return when (location) {
            Location.Russian -> {
                getRussianCities()
            }
            Location.World -> {
                getWorldCities()
            }
        }
    }
}