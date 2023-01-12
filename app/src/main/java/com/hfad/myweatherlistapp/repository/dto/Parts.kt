package com.hfad.myweatherlistapp.repository.dto


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Parts(
    @SerializedName("day")
    val day: Day,
    @SerializedName("day_short")
    val dayShort: DayShort,
    @SerializedName("evening")
    val evening: Evening,
    @SerializedName("morning")
    val morning: Morning,
    @SerializedName("night")
    val night: Night,
    @SerializedName("night_short")
    val nightShort: NightShort
) : Parcelable