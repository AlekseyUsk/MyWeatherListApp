package com.hfad.myweatherlistapp.repository.dto


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Info(
    @SerializedName("def_pressure_mm")
    val defPressureMm: Int,
    @SerializedName("def_pressure_pa")
    val defPressurePa: Int,
    @SerializedName("f")
    val f: Boolean,
    @SerializedName("_h")
    val h: Boolean,
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lon")
    val lon: Double,
    @SerializedName("n")
    val n: Boolean,
    @SerializedName("nr")
    val nr: Boolean,
    @SerializedName("ns")
    val ns: Boolean,
    @SerializedName("nsr")
    val nsr: Boolean,
    @SerializedName("p")
    val p: Boolean,
    @SerializedName("tzinfo")
    val tzinfo: Tzinfo,
    @SerializedName("url")
    val url: String,
    @SerializedName("zoom")
    val zoom: Int
) : Parcelable