package com.hfad.myweatherlistapp.viewmodel.list.citieslist


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hfad.myweatherlistapp.model.repository.Location
import com.hfad.myweatherlistapp.model.repository.RepositoryCitiesList
import com.hfad.myweatherlistapp.model.repository.RepositoryCitiesListImpl

class CitiesListViewModel(
    private val liveData: MutableLiveData<CityListFragmentAppState> = MutableLiveData<CityListFragmentAppState>(),
) :
    ViewModel() {

    lateinit var repositoryCitiesList: RepositoryCitiesList

    fun getLiveData(): MutableLiveData<CityListFragmentAppState> {
        choiceRepository()
        return liveData
    }

    private fun choiceRepository() {   // действия
        repositoryCitiesList = RepositoryCitiesListImpl()
    }

    private fun sentRequest(location: Location) {
        liveData.postValue(
            CityListFragmentAppState.SuccessMulti(
                repositoryCitiesList.getListCities(location)
            )
        )
    }

    fun getWeatherListForRussian(russian: Location.Russian) {
        sentRequest(Location.Russian)
    }

    fun getWeatherListForWorld() {
        sentRequest(Location.World)
    }

    fun isConnection(): Boolean {
        return false
    }
}
