package com.hfad.myweatherlistapp.model.list.room

import android.security.identity.AccessControlProfileId
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.net.IDN


@Entity(tableName = "history_entity_table")
data class HistoryEntity(

    @PrimaryKey(autoGenerate = true)

    val id: Long,
    val name: String,
    val lat: Double,
    val lon: Double,
    var temperature: Int = 0,
    var feelsLike: Int = 0
)