package com.hfad.myweatherlistapp.model.list.repository.retrofit

import com.google.gson.GsonBuilder
import com.hfad.myweatherlistapp.BuildConfig
import com.hfad.myweatherlistapp.domain.Weather
import com.hfad.myweatherlistapp.model.list.WeatherDTO
import com.hfad.myweatherlistapp.model.repository.MyLargeSuperCallback
import com.hfad.myweatherlistapp.model.repository.RepositoryLocationToOneWeather
import com.hfad.myweatherlistapp.utils.bindDTOWithCity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class RepositoryLocationToOneWeatherRetrofitImpl : RepositoryLocationToOneWeather {
    override fun getWeather(weather: Weather, callback: MyLargeSuperCallback) {
        val retrofitImpl = Retrofit.Builder()
        retrofitImpl.baseUrl("https://api.weather.yandex.ru")
        retrofitImpl.addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().setLenient().create()
            )
        )
        retrofitImpl.build()
        val api = retrofitImpl.build().create(WeatherAPI::class.java)
        api.getWeatherRetrofit(BuildConfig.BUILD_CONFIG_WEATHER_API_KEY, weather.city.lat, weather.city.lon)
            .enqueue(object : Callback<WeatherDTO> {
                override fun onResponse(call: Call<WeatherDTO>, response: Response<WeatherDTO>) {
                    if (response.isSuccessful && response.body() != null) {
                        callback.onResponse(bindDTOWithCity(response.body()!!,weather.city))
                    } else {
                        callback.onFailure(IOException("ошибка 403 404"))
                    }

                }

                override fun onFailure(call: Call<WeatherDTO>, t: Throwable) {
                    callback.onFailure(t as IOException) // костыль
                }

            })
    }
}
