package com.hfad.myweatherlistapp.view.details

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.hfad.myweatherlistapp.databinding.FragmentDetailsBinding
import com.hfad.myweatherlistapp.domain.Weather
import com.hfad.myweatherlistapp.repository.dto.WeatherDTO
import com.hfad.myweatherlistapp.utils.WeatherLoader
import com.hfad.myweatherlistapp.view.service.BUNDLE_CITY_KEY
import com.hfad.myweatherlistapp.view.service.BUNDLE_WEATHER_DTO_KEY
import com.hfad.myweatherlistapp.view.service.WAVE
import kotlinx.android.synthetic.main.fragment_details.*

class FragmentDetails : Fragment() {

    companion object {
        const val BUNDLE_WEATHER_EXTRA = "BUNDLE_WEATHER_EXTRA"
        fun newInstance(weather: Weather) = FragmentDetails().also { fr ->
            fr.arguments = Bundle().apply {
                putParcelable(BUNDLE_WEATHER_EXTRA, weather)
            }
        }
    }

    private var _binding: FragmentDetailsBinding? = null
    private val binding: FragmentDetailsBinding
        get() {
            return _binding!!
        }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val weather = arguments?.let { args -> args.getParcelable<Weather>(BUNDLE_WEATHER_EXTRA) }

        weather?.let { weatherLocal ->

            WeatherLoader.request(weatherLocal.city.lat, weatherLocal.city.lon) { weatherDTO ->
                bindWeatherLocalWeatherDTO(weatherLocal, weatherDTO as WeatherDTO)

                //настраиваем приемник
                LocalBroadcastManager.getInstance(requireContext()).registerReceiver(
                    object : BroadcastReceiver() {
                        override fun onReceive(context: Context?, intent: Intent?) {
                            Log.d("@@@", "onReceive ${binding.root}")
                            intent?.let {
                                it.getParcelableExtra<WeatherDTO>(BUNDLE_WEATHER_DTO_KEY)
                                    ?.let { weatherDTO ->
                                        bindWeatherLocalWeatherDTO(weatherLocal, weatherDTO)
                                    }
                            }

                        }
                    }, IntentFilter(WAVE)
                )
                //запускаем сервис и передаем город
                requireActivity().startService(Intent().apply {
                    putExtra(BUNDLE_CITY_KEY, weatherLocal.city)
                })

            }
        }
    }

    private fun bindWeatherLocalWeatherDTO(weatherLocal: Weather, weatherDTO: WeatherDTO) {
        requireActivity().runOnUiThread {
            renderData(weatherLocal.apply {
                weatherLocal.feelsLike = weatherDTO.fact.feelsLike
                weatherLocal.temperature = weatherDTO.fact.temp
            })
        }
    }

    fun renderData(weather: Weather) {
        with(binding) {
            cityName.text = weather.city.name
            temperatureValue.text = weather.temperature.toString()
            feelsLikeValue.text = weather.feelsLike.toString()
            cityCoordinates.text = "${weather.city.lat}/${weather.city.lon}"
        }

    }
}