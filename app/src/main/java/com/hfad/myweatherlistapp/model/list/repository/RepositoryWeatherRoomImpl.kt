package com.hfad.myweatherlistapp.model.repository


import com.hfad.myweatherlistapp.MyApp
import com.hfad.myweatherlistapp.domain.City
import com.hfad.myweatherlistapp.domain.Weather
import com.hfad.myweatherlistapp.model.list.room.HistoryEntity


class RepositoryWeatherRoomImpl : RepositoryLocationToOneWeather, RepositoryWeatherAddable {
    override fun getWeather(weather: Weather, callback: MyLargeSuperCallback) {
        callback.onResponse(MyApp.getWeatherDateBase().weatherDao().getWeatherOne(weather.city.lat, weather.city.lon).let {
            convertHistoryEntityToWeather(it).last()
        })
    }

    override fun addWeather(weather: Weather) {
        MyApp.getWeatherDateBase().weatherDao().insertRoom(convertWeatherToEntity(weather))
    }

   private fun convertWeatherToEntity(weather: Weather): HistoryEntity {
        return HistoryEntity(
            0,
            weather.city.name,
            weather.city.lat,
            weather.city.lon,
            weather.temperature,
            weather.feelsLike
        )
    }

   private fun convertHistoryEntityToWeather(entityList: List<HistoryEntity>): List<Weather> {
        return entityList.map {
            Weather(City(it.name, it.lat, it.lon), it.temperature, it.feelsLike)
        }
    }
}
