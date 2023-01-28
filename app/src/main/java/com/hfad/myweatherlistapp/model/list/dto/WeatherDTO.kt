package com.hfad.myweatherlistapp.model.list


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WeatherDTO(
    @SerializedName("fact")
    val fact: Fact,
    @SerializedName("info")
    val info: Info,
    ): Parcelable