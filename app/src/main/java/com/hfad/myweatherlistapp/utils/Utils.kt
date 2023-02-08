package com.hfad.myweatherlistapp.utils

import android.os.Build
import androidx.annotation.RequiresApi
import com.hfad.myweatherlistapp.domain.City
import com.hfad.myweatherlistapp.domain.Weather
import com.hfad.myweatherlistapp.model.list.Fact
import com.hfad.myweatherlistapp.model.list.WeatherDTO
import java.io.BufferedReader
import java.util.stream.Collectors

@RequiresApi(Build.VERSION_CODES.N)
fun getLines(reader: BufferedReader): String {
    return reader.lines().collect(Collectors.joining("\n"))
}

fun bindDTOWithCity(weatherDTO: WeatherDTO,city: City): Weather {
    val fact: Fact = weatherDTO.fact
    return (Weather(city, fact.temp, fact.feelsLike))
}

fun convertModelToDto(weather: Weather): WeatherDTO {
    val fact: Fact = Fact(weather.feelsLike, weather.temperature)
    return WeatherDTO(fact)
}
