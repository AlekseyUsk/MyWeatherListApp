package com.hfad.myweatherlistapp.model.list


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Fact(
    @SerializedName("feels_like")
    val feelsLike: Int,
    @SerializedName("temp")
    val temp: Int,
) : Parcelable