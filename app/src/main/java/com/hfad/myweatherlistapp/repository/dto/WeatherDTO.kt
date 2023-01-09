package com.hfad.myweatherlistapp.repository.dto


import com.google.gson.annotations.SerializedName

data class WeatherDTO(
    @SerializedName("fact")
    val fact: Fact,
    @SerializedName("info")
    val info: Info,

)