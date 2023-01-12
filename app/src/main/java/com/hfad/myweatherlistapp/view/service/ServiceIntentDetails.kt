package com.hfad.myweatherlistapp.view.service

import android.app.IntentService
import android.content.Intent
import android.os.Build
import android.os.Handler
import android.os.Looper
import androidx.annotation.RequiresApi
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.gson.Gson
import com.hfad.myweatherlistapp.BuildConfig
import com.hfad.myweatherlistapp.domain.City
import com.hfad.myweatherlistapp.repository.dto.WeatherDTO
import com.hfad.myweatherlistapp.utils.YANDEX_API_KEY
import com.hfad.myweatherlistapp.utils.getLines
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection

const val WAVE = "wave"
const val BUNDLE_WEATHER_DTO_KEY = "bundle_weather_dto_key"
const val BUNDLE_CITY_KEY = "bundle_city_key"


// ИЗ СЕРВИСА ДЕЛАЕМ ЗАПРОС В ЯНДЕКС (ТУТ ИДЕТ НАРУШЕНИЕ mvvm)
class ServiceIntentDetails : IntentService("") {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onHandleIntent(intent: Intent?) {
        intent?.let {
            it.getParcelableExtra<City>(BUNDLE_CITY_KEY)?.let {
                val uri =
                    URL("https://api.weather.yandex.ru/v2/forecast?lat=${it.lat}&lon=${it.lon}")

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

                        LocalBroadcastManager.getInstance(this).sendBroadcast(Intent().apply {
                            putExtra(BUNDLE_WEATHER_DTO_KEY, weatherDTO)
                            action = WAVE
                        })

                    } catch (e: java.lang.Exception) {

                    } finally {
                        myConnection.disconnect()
                    }
                }.start()
            }
        }
    }
}