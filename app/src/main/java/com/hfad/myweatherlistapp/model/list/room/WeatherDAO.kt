package com.hfad.myweatherlistapp.model.list.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WeatherDAO {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRoom(historyEntity: HistoryEntity)

    @Query("SELECT * FROM history_entity_table WHERE lat=:mlat AND lon=:mlon")
    fun getWeatherOne(mlat: Double, mlon: Double):List<HistoryEntity>


    @Query("SELECT * FROM history_entity_table")
    fun getWeatherAll():List<HistoryEntity>
}
