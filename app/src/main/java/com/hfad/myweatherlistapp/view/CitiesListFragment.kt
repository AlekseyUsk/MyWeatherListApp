package com.hfad.myweatherlistapp.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.hfad.myweatherlistapp.R
import com.hfad.myweatherlistapp.databinding.FragmentWeatherListBinding
import com.hfad.myweatherlistapp.domain.Weather
import com.hfad.myweatherlistapp.utils.SP_BD_IS_RUSSIA
import com.hfad.myweatherlistapp.utils.SP_KEY_IS_RUSSIA
import com.hfad.myweatherlistapp.view.details.FragmentDetails
import com.hfad.myweatherlistapp.view.details.callback.OnItemClick
import com.hfad.myweatherlistapp.viewmodel.list.citieslist.CitiesListViewModel
import com.hfad.myweatherlistapp.viewmodel.list.citieslist.CityListFragmentAppState


class CitiesListFragment : Fragment(), OnItemClick {

    var isRussia: Boolean = true

    companion object {
        fun newInstance(): CitiesListFragment {
            return CitiesListFragment()
        }
    }

    private lateinit var viewModel: CitiesListViewModel

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){

        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(CitiesListViewModel::class.java)
        viewModel.getLiveData().observe(viewLifecycleOwner, object : Observer<CityListFragmentAppState> {
            override fun onChanged(t: CityListFragmentAppState) {  //приходят ответы из LiveData
                renderData(t)
            }
        })


        //TODO SP НЕМОГУ РАЗОБРАТЬСЯ КАК СДЕЛАТЬ СОХРАНЕНИЕ ВЫБОРА ГОРОДОВ МИРА ИЛИ РФ
        val sharedPreferences = requireActivity().getSharedPreferences(SP_BD_IS_RUSSIA, Context.MODE_PRIVATE)

        binding.floatingBtn.setOnClickListener {
            isRussia = !isRussia
            if (isRussia){
                viewModel.getWeatherListForRussian()
            }else{
                viewModel.getWeatherListForWorld()
            }
            sharedPreferences.edit().apply(){
                putBoolean(SP_KEY_IS_RUSSIA, isRussia)
                isRussia = sharedPreferences.getBoolean(SP_KEY_IS_RUSSIA,isRussia)
                apply()
            }
        }
        viewModel.ChoiceCitiesRussiaAndWorld(isRussia)




    }

    private fun renderData(cityListFragmentAppState: CityListFragmentAppState) {   //реакция на запросы
        when (cityListFragmentAppState) {
            is CityListFragmentAppState.Error -> {
                binding.showResult()
            }
            CityListFragmentAppState.Loading -> {
                binding.loading()
            }
            is CityListFragmentAppState.SuccessOne -> {
                binding.showResult()
                val result = cityListFragmentAppState.weatherData
            }
            is CityListFragmentAppState.SuccessMulti -> {
                binding.showResult()
                binding.FragmentRecyclerView.adapter =
                    CitiesListAdapter(cityListFragmentAppState.weatherList, this)
            }
        }
    }

    fun FragmentWeatherListBinding.loading() {
        this.FragmentLoadingLayout.visibility = View.VISIBLE
        this.floatingBtn.visibility = View.GONE
    }

    fun FragmentWeatherListBinding.showResult() {
        this.FragmentLoadingLayout.visibility = View.GONE
        this.floatingBtn.visibility = View.VISIBLE
    }

    override fun onItemClick(weather: Weather) {
        (binding.root.context as MainActivity).supportFragmentManager.beginTransaction().hide(this)
            .add(R.id.container, FragmentDetails.newInstance(weather)).addToBackStack("").commit()
    }
}
