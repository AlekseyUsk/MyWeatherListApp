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
    fun request(lat: Double, lon: Double, onResponse: (Any) -> Unit) : Unit{

        val uri = URL("https://api.weather.yandex.ru/v2/forecast?lat=${lat}&lon=${lon}")
        var myConnection: HttpsURLConnection? = null

        myConnection = uri.openConnection() as HttpsURLConnection
        myConnection.readTimeout = 3000
        myConnection.addRequestProperty("X-Yandex-API-Key",BuildConfig.WEATHER_API_KEY
        )
        val handler = Handler(Looper.myLooper()!!)
        Thread {
            val reader = BufferedReader(InputStreamReader(myConnection.inputStream)) // читаем поток запроса
            val weatherDTO = Gson().fromJson(getLines(reader), WeatherDTO::class.java)
            onResponse(weatherDTO)
        }.start()
    }
}

//"761b1538-ff90-4863-b944-568f28907683"
