package com.hfad.myweatherlistapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hfad.myweatherlistapp.databinding.FragmentCitiesListRecyclerItemBinding
import com.hfad.myweatherlistapp.domain.Weather
import com.hfad.myweatherlistapp.view.details.callback.OnItemClick

class CitiesListAdapter(private val dataList: List<Weather>, private val callback:OnItemClick) :
    RecyclerView.Adapter<CitiesListAdapter.WeatherViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val binding = FragmentCitiesListRecyclerItemBinding.inflate(LayoutInflater.from(parent.context))
        return WeatherViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int {
       return dataList.size
    }

   inner class WeatherViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(weather: Weather) {
            val binding = FragmentCitiesListRecyclerItemBinding.bind(itemView)
            with(binding){
                this.cityName.text = weather.city.name
                this.root.setOnClickListener {
                    callback.onItemClick(weather)
            }

            }
        }
    }
}