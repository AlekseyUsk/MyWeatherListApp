package com.hfad.myweatherlistapp.model.repository

import com.google.gson.Gson
import com.hfad.myweatherlistapp.BuildConfig
import com.hfad.myweatherlistapp.domain.Weather
import com.hfad.myweatherlistapp.model.list.WeatherDTO
import com.hfad.myweatherlistapp.utils.YANDEX_API_KEY
import com.hfad.myweatherlistapp.utils.bindDTOWithCity
import okhttp3.*
import java.io.IOException

class RepositoryLocationToOneWeatherOkHttpImpl : RepositoryLocationToOneWeather {
    override fun getWeather(weather: Weather, callback: MyLargeSuperCallback) {
                                                                                                                              // Асинхронный запрос
        val client = OkHttpClient()                                                                                           // Клиент
        val builder: Request.Builder = Request.Builder()                                                                     // Создаём строителя запроса
        builder.header(YANDEX_API_KEY, BuildConfig.BUILD_CONFIG_WEATHER_API_KEY)                                          // Создаём заголовок запроса
        builder.url("https://api.weather.yandex.ru/v2/forecast?lat=${weather.city.lat}&lon=${weather.city.lon}")                                       // Формируем URL
        val request: Request = builder.build()                                                                               // Создаём запрос
        val call: Call = client.newCall(request)                                                                             // Ставим запрос в очередь и отправляем

        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                callback.onFailure(e)
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.code in 200..299 && response.body != null) {
                    response.body?.let {
                        val responseString = it.string()
                        val weatherDTO = Gson().fromJson((responseString), WeatherDTO::class.java)
                     callback.onResponse(bindDTOWithCity(weatherDTO,weather.city))
                        }
                } else {
                   callback.onFailure(IOException("ошибка 403 404"))
                }
            }
        })
    }
}
