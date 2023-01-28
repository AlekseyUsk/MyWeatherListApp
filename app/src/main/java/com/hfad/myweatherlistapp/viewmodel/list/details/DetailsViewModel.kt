package com.hfad.myweatherlistapp.viewmodel.list.details


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hfad.myweatherlistapp.model.list.repository.RepositoryDetailsRetrofitImpl
import com.hfad.myweatherlistapp.model.list.repository.RepositoryDetailsWeatherLoaderImpl
import com.hfad.myweatherlistapp.model.repository.RepositoryDetails
import com.hfad.myweatherlistapp.model.repository.RepositoryDetailsLocalImpl
import com.hfad.myweatherlistapp.model.repository.RepositoryDetailsOkHttpImpl

class DetailsViewModel(private val liveData: MutableLiveData<DetailsFragmentAppState> = MutableLiveData<DetailsFragmentAppState>(),) : ViewModel() {

    lateinit var repository: RepositoryDetails

    fun getLiveData(): MutableLiveData<DetailsFragmentAppState> {
        choiceRepository()
        return liveData
    }

    private fun choiceRepository() {
        repository = when (1) {
            1 -> { RepositoryDetailsOkHttpImpl()}
            2 -> { RepositoryDetailsRetrofitImpl()}
            3 -> { RepositoryDetailsWeatherLoaderImpl()}
            else -> { RepositoryDetailsLocalImpl()}
        }
    }

    fun getWeather(lat: Double, lon: Double) {
        choiceRepository()
        liveData.value = DetailsFragmentAppState.Loading
        // Ошибку ERROR
        liveData.postValue(DetailsFragmentAppState.Success(repository.getWeather(lat, lon)))
    }

    fun isConnection(): Boolean {
        return false
    }
}
