package com.hfad.myweatherlistapp.model.list.repository.retrofit

import com.hfad.myweatherlistapp.model.list.WeatherDTO
import com.hfad.myweatherlistapp.utils.YANDEX_API_KEY
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface WeatherAPI {

    @GET("/v2/forecast?")
    fun getWeatherRetrofit(
        @Header(YANDEX_API_KEY) keyValue: String,
        @Query("lat") lat: Double,
        @Query("lon") lon: Double
    ) : Call<WeatherDTO>

}