package com.hfad.myweatherlistapp.model.list.room

import androidx.room.Entity
import androidx.room.PrimaryKey


//даем анатацию что эта будет таблиц и даем псевдоним
@Entity(tableName = "history_entity_table")
data class HistoryEntity(

    @PrimaryKey(autoGenerate = true) // указываем что это поле яв-ся уникальным ключом и сущьностью
    // в параметрах указываем автогенерацию для того что бы не передовать id  на вход оно само сгенерируется
    val idn: Long, // задаем id
    val name: String,
    val lat: Double,
    val lon: Double,
    var temperature: Int = 0,
    var feelsLike: Int = 0
)