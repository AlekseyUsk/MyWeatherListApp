package com.hfad.myweatherlistapp.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hfad.myweatherlistapp.model.Repository
import com.hfad.myweatherlistapp.model.RepositoryLocalImpl
import com.hfad.myweatherlistapp.model.RepositoryRemoteImpl
import java.lang.Thread.sleep

class WeatherListViewModel(
    private val liveData: MutableLiveData<AppState> = MutableLiveData<AppState>(),
    var repository: Repository
) :
    ViewModel() {

    fun getLiveData(): MutableLiveData<AppState> {
        choiceRepository()
        return liveData
    }

    private fun choiceRepository() {
        if (isConnection()) {
            repository = RepositoryRemoteImpl()
        } else {
            repository = RepositoryLocalImpl()
        }
    }

    fun sentRequest() {
        choiceRepository()
        if (isConnection())
            liveData.value = AppState.Loading
        liveData.postValue(AppState.Success(repository.getWeather(55.755826, 37.617299900000035)))

    }

    private fun isConnection(): Boolean {
        return false
    }
}