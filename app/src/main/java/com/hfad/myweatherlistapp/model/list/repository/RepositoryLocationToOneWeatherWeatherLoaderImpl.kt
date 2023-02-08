package com.hfad.myweatherlistapp.model.list.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.gson.Gson
import com.hfad.myweatherlistapp.BuildConfig
import com.hfad.myweatherlistapp.domain.Weather
import com.hfad.myweatherlistapp.model.list.WeatherDTO
import com.hfad.myweatherlistapp.model.repository.MyLargeSuperCallback
import com.hfad.myweatherlistapp.model.repository.RepositoryLocationToOneWeather
import com.hfad.myweatherlistapp.utils.YANDEX_API_KEY
import com.hfad.myweatherlistapp.utils.bindDTOWithCity
import com.hfad.myweatherlistapp.utils.getLines
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class RepositoryLocationToOneWeatherWeatherLoaderImpl : RepositoryLocationToOneWeather {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun getWeather(weather: Weather, callback: MyLargeSuperCallback) {
        Thread {
            val uri = URL("https://api.weather.yandex.ru/v2/forecast?lat=${weather.city.lat}&lon=${weather.city.lon}")
            var myConnection: HttpsURLConnection? = null
            myConnection = uri.openConnection() as HttpsURLConnection
            try {
                myConnection.readTimeout = 5000
                myConnection.addRequestProperty(YANDEX_API_KEY, BuildConfig.BUILD_CONFIG_WEATHER_API_KEY)

                val reader = BufferedReader(InputStreamReader(myConnection.inputStream))
                val weatherDTO = Gson().fromJson(getLines(reader), WeatherDTO::class.java)
                callback.onResponse(bindDTOWithCity(weatherDTO,weather.city))
            } catch (e: IOException) {
                callback.onFailure(e)
            } finally {
                myConnection.disconnect()
            }
        }.start()
    }
}


