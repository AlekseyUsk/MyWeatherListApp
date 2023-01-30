package com.hfad.myweatherlistapp.model.repository

import com.hfad.myweatherlistapp.domain.Weather
import com.hfad.myweatherlistapp.domain.getDefaultCity
import com.hfad.myweatherlistapp.domain.getRussianCities
import com.hfad.myweatherlistapp.domain.getWorldCities
import com.hfad.myweatherlistapp.model.list.Fact
import com.hfad.myweatherlistapp.model.list.WeatherDTO

class RepositoryDetailsLocalImpl : RepositoryDetails {

    override fun getWeather(lat: Double, lon: Double, callback: MyLargeSuperCallback) {
        val list = getWorldCities().toMutableList() //сделал мутабельный список
        list.addAll(getRussianCities()) // добавил города россии в этот список
        //после того как скомпоновал список
        val response = list.filter { it.city.lat == lat && it.city.lon == lon }
        callback.onResponse(convertModelToDto(response.first()))
    }

   private fun convertDtoToModel(weatherDTO: WeatherDTO): Weather {
        val fact: Fact = weatherDTO.fact
        return (Weather(getDefaultCity(), fact.temp, fact.feelsLike))
    }

   private fun convertModelToDto(weather: Weather): WeatherDTO {
        val fact: Fact = Fact(weather.feelsLike, weather.temperature)
        return WeatherDTO(fact)
    }

}
