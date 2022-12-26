package com.hfad.myweatherlistapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hfad.myweatherlistapp.databinding.FragmentWeatherListBinding
import com.hfad.myweatherlistapp.domain.Weather

class WeatherListAdapter(val dataList: List<Weather>) :
    RecyclerView.Adapter<WeatherListAdapter.WeatherViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val binding = FragmentWeatherListBinding.inflate(LayoutInflater.from(parent.context))
        return WeatherViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int {
       return dataList.size
    }

    class WeatherViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(weather: Weather) {
            val binding = FragmentWeatherListBinding.bind(itemView)
            binding.cityName.text = weather.city.name // ГОРИТ КРАССНЫМ!!!!
        }
    }
}