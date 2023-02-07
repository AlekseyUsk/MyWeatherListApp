package com.hfad.myweatherlistapp

import android.app.Application
import androidx.room.Room
import com.hfad.myweatherlistapp.model.list.room.WeatherDataBase
import com.hfad.myweatherlistapp.utils.ROOM_BD_WEATHER

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        myApp = this
    }

    companion object {
        private var myApp: MyApp? = null
        fun getMyApp() = myApp!!

        // БАЗА ДАННЫХ
        private var weatherDataBase: WeatherDataBase? = null
        fun getWeatherDateBase():WeatherDataBase {
            if (weatherDataBase == null) {
                weatherDataBase = Room.databaseBuilder(getMyApp(),WeatherDataBase::class.java,ROOM_BD_WEATHER).allowMainThreadQueries().build()//FIXME allowMainThreadQueries()
            }
        return weatherDataBase!!
        }
    }
}
