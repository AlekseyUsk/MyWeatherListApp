package com.hfad.myweatherlistapp.view

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import androidx.lifecycle.Observer
import com.hfad.myweatherlistapp.databinding.ActivityMainBinding
import com.hfad.myweatherlistapp.viewmodel.AppState
import com.hfad.myweatherlistapp.viewmodel.WeatherListViewModel


class WeatherListFragment : Fragment() {

    companion object {
        fun newInstance() = Fragment()
    }

    private lateinit var viewModel: WeatherListViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ActivityMainBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(WeatherListViewModel::class.java)
        viewModel.getLiveData().observe(viewLifecycleOwner, object : Observer<AppState> {
            override fun onChanged(t: AppState) {
                renderData(t)
            }

        })
        viewModel.sentRequest()
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Error -> TODO()
            AppState.Loading -> TODO()
            is AppState.Success -> {
                val result = appState.weatherData
                binding.cityName.text = result.cityName // !!!!! binding НЕВИДИТ XML
                // ДАЛЬШЕ НЕМОГУ ОТРИСОВАТЬ
                Toast.makeText(requireContext(), "работает$result", Toast.LENGTH_LONG).show()

            }
        }

    }
}