package com.hfad.myweatherlistapp.utils

import android.os.Build
import android.os.Handler
import android.os.Looper
import androidx.annotation.RequiresApi
import com.google.gson.Gson
import com.hfad.myweatherlistapp.BuildConfig
import com.hfad.myweatherlistapp.model.list.WeatherDTO
import com.hfad.myweatherlistapp.view.details.callback.OnResponse
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection

object WeatherLoader {


    @RequiresApi(Build.VERSION_CODES.N)
    fun request(lat: Double, lon: Double, onResponse: OnResponse): Unit {

        val uri = URL("https://api.weather.yandex.ru/v2/forecast?lat=${lat}&lon=${lon}")

        Thread {
            var myConnection: HttpsURLConnection? = null
            myConnection = uri.openConnection() as HttpsURLConnection
            try {
                myConnection.readTimeout = 200
                myConnection.addRequestProperty(YANDEX_API_KEY, BuildConfig.WEATHER_API_KEY)
                val handler = Handler(Looper.myLooper()!!)
                val reader =
                    BufferedReader(InputStreamReader(myConnection.inputStream)) // читаем поток запроса
                val weatherDTO = Gson().fromJson(getLines(reader), WeatherDTO::class.java)
                onResponse(weatherDTO)
            } catch (e: java.lang.Exception) {

            } finally {
                myConnection.disconnect()
            }
        }.start()
    }

    private fun onResponse(weatherDTO: WeatherDTO?) {}

    @RequiresApi(Build.VERSION_CODES.N)
    fun requestSecondVariant(lat: Double, lon: Double, block: (weather: WeatherDTO) -> Unit) {
        val uri = URL("https://api.weather.yandex.ru/v2/forecast?lat=${lat}=lon${lon}")

        Thread {
            var myConnection: HttpsURLConnection? = null
            myConnection = uri.openConnection() as HttpsURLConnection
            try {
                myConnection.readTimeout = 200
                myConnection.addRequestProperty(YANDEX_API_KEY, BuildConfig.WEATHER_API_KEY)
                val reader = BufferedReader(InputStreamReader(myConnection.inputStream))
                val weatherDTO = Gson().fromJson(getLines(reader), WeatherDTO::class.java)
                block(weatherDTO)
            } catch (e: java.lang.Exception) {

            } finally {
                myConnection.disconnect()
            }
        }
    }
}
