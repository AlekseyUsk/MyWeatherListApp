package com.hfad.myweatherlistapp.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.lifecycle.Observer
import com.hfad.myweatherlistapp.R
import com.hfad.myweatherlistapp.databinding.FragmentWeatherListBinding
import com.hfad.myweatherlistapp.domain.Weather
import com.hfad.myweatherlistapp.view.details.FragmentDetails
import com.hfad.myweatherlistapp.view.details.callback.OnItemClick
import com.hfad.myweatherlistapp.viewmodel.AppState
import com.hfad.myweatherlistapp.viewmodel.WeatherListViewModel


class WeatherListFragment : Fragment(), OnItemClick {

    var isRussia: Boolean = true

    companion object {
        fun newInstance() = Fragment()
    }

    private lateinit var viewModel: WeatherListViewModel

    private var _binding: FragmentWeatherListBinding? = null
    private val binding: FragmentWeatherListBinding
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
        _binding = FragmentWeatherListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(WeatherListViewModel::class.java)
        viewModel.getLiveData().observe(viewLifecycleOwner, object : Observer<AppState> {
            override fun onChanged(t: AppState) {  //приходят ответы из LiveData
                renderData(t)
            }
        })

        binding.floatingBtn.setOnClickListener {
            isRussia = !isRussia
            if (isRussia) {
                viewModel.getWeatherListForRussian()
            } else {
                viewModel.getWeatherListForWorld()
            }
        }
        // viewModel.getWeatherListForRussian(Location.Russian) дефолтное поставлю потом
    }

    private fun renderData(appState: AppState) {   //реакция на запросы
        when (appState) {
            is AppState.Error -> {/* TODO()*/
            }
            AppState.Loading -> {/* TODO()*/
            }
            is AppState.SuccessOne -> {
                val result = appState.weatherData
            }
            is AppState.SuccessMulti -> {
                binding.FragmentRecyclerView.adapter =
                    WeatherListAdapter(appState.weatherList, this)
            }
        }
    }

    override fun onItemClick(weather: Weather) {
        (binding.root.context as MainActivity).supportFragmentManager.beginTransaction().hide(this)
            .add(R.id.container, FragmentDetails.newInstance(weather)).addToBackStack("").commit()
    }
}