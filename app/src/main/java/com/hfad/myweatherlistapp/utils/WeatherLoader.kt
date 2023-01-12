package com.hfad.myweatherlistapp.utils

import android.os.Build
import android.os.Handler
import android.os.Looper
import androidx.annotation.RequiresApi
import com.google.gson.Gson
import com.hfad.myweatherlistapp.BuildConfig
import com.hfad.myweatherlistapp.repository.dto.WeatherDTO
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection

object WeatherLoader {


    @RequiresApi(Build.VERSION_CODES.N)
    fun request(lat: Double, lon: Double, onResponse: (Any) -> Unit): Unit {

        val uri = URL("https://api.weather.yandex.ru/v2/forecast?lat=${lat}&lon=${lon}")

        Thread {
            var myConnection: HttpsURLConnection? = null
            myConnection = uri.openConnection() as HttpsURLConnection
            try {
                myConnection.readTimeout = 3000
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
}
