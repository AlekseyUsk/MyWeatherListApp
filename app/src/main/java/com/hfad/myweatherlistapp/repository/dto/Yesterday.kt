package com.hfad.myweatherlistapp.repository.dto


import com.google.gson.annotations.SerializedName

data class Yesterday(
    @SerializedName("temp")
    val temp: Int
)