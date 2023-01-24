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
import com.google.gson.Gson
import com.hfad.myweatherlistapp.BuildConfig
import com.hfad.myweatherlistapp.databinding.FragmentDetailsBinding
import com.hfad.myweatherlistapp.domain.Weather
import com.hfad.myweatherlistapp.repository.dto.WeatherDTO
import com.hfad.myweatherlistapp.utils.YANDEX_API_KEY
import com.hfad.myweatherlistapp.view.service.BUNDLE_CITY_KEY
import com.hfad.myweatherlistapp.view.service.BUNDLE_WEATHER_DTO_KEY
import com.hfad.myweatherlistapp.view.service.ServiceIntentDetails
import com.hfad.myweatherlistapp.view.service.WAVE
import kotlinx.android.synthetic.main.fragment_details.*
import okhttp3.*
import java.io.IOException

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

    lateinit var weatherLocal: Weather

    val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            Log.d("@@@", "onReceive ${binding.root}")
            intent?.let {
                it.getParcelableExtra<WeatherDTO>(BUNDLE_WEATHER_DTO_KEY)
                    ?.let { weatherDTO ->
                        bindWeatherLocalWeatherDTO(weatherLocal, weatherDTO)
                    }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        LocalBroadcastManager.getInstance(requireContext()).unregisterReceiver(receiver)
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
            this.weatherLocal = weatherLocal

            LocalBroadcastManager.getInstance(requireContext())
                .registerReceiver(receiver, IntentFilter(WAVE))//настраиваем приемник на ответ

            requireActivity().startService(
                Intent(
                    requireContext(),
                    ServiceIntentDetails::class.java
                ).apply {//запускаем сервис и передаем город
                    putExtra(BUNDLE_CITY_KEY, weatherLocal.city)
                })
        }
            // Синхронный запрос
//        val client = OkHttpClient() // Клиент
//        val builder: Request.Builder = Request.Builder() // Создаём строителя запроса
//        builder.header(YANDEX_API_KEY, BuildConfig.WEATHER_API_KEY) // Создаём заголовок запроса
//        builder.url("https://api.weather.yandex.ru/v2/forecast?lat=${weatherLocal.city.lat}&lon=${weatherLocal.city.lon}") // Формируем URL
//        val request: Request = builder.build() // Создаём запрос
//        val call: Call = client.newCall(request) // Ставим запрос в очередь и отправляем
//        Thread {
//            val response = call.execute()
//            if (response.isSuccessful) {
//
//            }
//            if (response.code in 200..299) {
//                response.body?.let {
//                    val responseString = it.string()
//                    val weatherDTO = Gson().fromJson((responseString), WeatherDTO::class.java)
//                    weatherLocal.feelsLike = weatherDTO.fact.feelsLike
//                    weatherLocal.temperature = weatherDTO.fact.temp
//                    requireActivity().runOnUiThread {
//                        renderData(weatherLocal)
//                    }
//                }
//            }
//        }.start()
            // Асинхронный запрос
        val client = OkHttpClient() // Клиент
        val builder: Request.Builder = Request.Builder() // Создаём строителя запроса
        builder.header(YANDEX_API_KEY, BuildConfig.WEATHER_API_KEY) // Создаём заголовок запроса
        builder.url("https://api.weather.yandex.ru/v2/forecast?lat=${weatherLocal.city.lat}&lon=${weatherLocal.city.lon}") // Формируем URL
        val request: Request = builder.build() // Создаём запрос
        val call: Call = client.newCall(request) // Ставим запрос в очередь и отправляем

        call.enqueue(object : Callback{
            override fun onFailure(call: Call, e: IOException) {
//Метод onFailure вызывается, когда не удалось отправить запрос на сервер, то есть
//какая-то ошибка произошла на вашей стороне. Если вызывается onResponse, это значит, что
//ответ от сервера пришёл. Но это не значит, что пришли нужные вам данные. Может прийти
//ошибка сервера — это тоже ответ. Поэтому вы должны проверять ответ на наличие требуемых
//вам данных.
            }
            override fun onResponse(call: Call, response: Response) {
              //  if (response.isSuccessful){} либо так либо как ниже способ при удачном запросе
                if (response.code in 200..299&&response.body !=null) {
                    response.body?.let {
                        val responseString = it.string()
                        val weatherDTO = Gson().fromJson((responseString), WeatherDTO::class.java)
                        weatherLocal.feelsLike = weatherDTO.fact.feelsLike
                        weatherLocal.temperature = weatherDTO.fact.temp
                        requireActivity().runOnUiThread {
                            renderData(weatherLocal)
                        }
                    }
                }else{
                    //если нет то вывести ошибку
                }
            }
        })
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