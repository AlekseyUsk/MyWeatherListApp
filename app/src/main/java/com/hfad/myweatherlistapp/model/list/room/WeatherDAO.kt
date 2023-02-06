package com.hfad.myweatherlistapp.model.list.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

// интерфейс работы с таблицей указываем антоцию что это интерфейс по работе с базой данных
@Dao
interface WeatherDAO {

//анотация вставки данныхс таблицы тут же ее и передали в параметрах указываем onConflict этот параметр отвечает за то что при запросе
// непришел одинаковый ответ/конфликт и там есть выбор команд читай в инете ту на примере указал зареплейсить

    @Insert(onConflict = OnConflictStrategy.REPLACE)            // C
    fun insertRoom(historyEntity: HistoryEntity)

    // еще функцию получения добавим
    // * означает все поля хочу из такой то таблице и такие то параметры
    @Query("SELECT * FROM history_entity_table WHERE lat=:mlat AND lon=:mlon") //!!! SELECT FROM WHERE lat=:mlat AND lon=:mlon - нет документации тут простой строкой стоят
    fun getWeatherOne(mlat: Double, mlon: Double):List<HistoryEntity>


    @Query("SELECT * FROM history_entity_table") //!!! SELECT FROM WHERE lat=:mlat AND lon=:mlon - нет документации тут простой строкой стоят
    fun getWeatherAll(mlat: Double, mlon: Double):List<HistoryEntity>
}
