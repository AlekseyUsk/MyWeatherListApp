package com.hfad.myweatherlistapp.model.list.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(HistoryEntity::class), version = 1)
abstract class WeatherDataBase:RoomDatabase() {
    abstract fun weatherDao():WeatherDAO
}