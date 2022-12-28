package com.hfad.myweatherlistapp.view.details

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.hfad.myweatherlistapp.databinding.FragmentDetailsBinding
import com.hfad.myweatherlistapp.domain.Weather
import com.hfad.myweatherlistapp.view.WeatherListFragment
import kotlinx.android.synthetic.main.fragment_details.*

class FragmentDetails : Fragment() {

    companion object {
        const val BUNDLE_WEATHER_EXTRA = "BUNDLE_WEATHER_EXTRA"
        fun newInstance(weather: Weather): FragmentDetails {
            val bundle = Bundle()
            bundle.putParcelable(BUNDLE_WEATHER_EXTRA, weather)
            val fr = FragmentDetails()
            fr.arguments = bundle
            return fr
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

       val weather = arguments?.getParcelable<Weather>(BUNDLE_WEATHER_EXTRA)
        renderData(weather)
    }

    private fun renderData(weather: Weather?) {
        if (weather != null) {
            cityName.text = weather.city.name
            temperatureValue.text = weather.temperature.toString()
            feelsLikeValue.text = weather.feelsLike.toString()
            cityCoordinates.text = "${weather.city.lat}/${weather.city.lon}"
        }

    }
}
