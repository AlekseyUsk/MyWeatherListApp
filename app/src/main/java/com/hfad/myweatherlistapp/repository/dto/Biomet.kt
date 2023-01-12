package com.hfad.myweatherlistapp.repository.dto


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Biomet(
    @SerializedName("condition")
    val condition: String,
    @SerializedName("index")
    val index: Int
) : Parcelable