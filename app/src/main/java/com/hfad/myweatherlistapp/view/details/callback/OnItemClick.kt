package com.hfad.myweatherlistapp.view.details.callback

import com.hfad.myweatherlistapp.domain.Weather

fun interface OnItemClick {
    fun onItemClick(weather: Weather)
}