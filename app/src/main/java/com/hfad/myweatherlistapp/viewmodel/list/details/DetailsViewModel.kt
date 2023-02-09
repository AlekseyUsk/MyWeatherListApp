package com.hfad.myweatherlistapp.viewmodel.list.details


import android.os.Handler
import android.os.Looper
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hfad.myweatherlistapp.domain.Weather
import com.hfad.myweatherlistapp.model.list.repository.RepositoryLocationToOneWeatherWeatherLoaderImpl
import com.hfad.myweatherlistapp.model.list.repository.RepositoryRoomImpl
import com.hfad.myweatherlistapp.model.list.repository.retrofit.RepositoryLocationToOneWeatherRetrofitImpl
import com.hfad.myweatherlistapp.model.repository.*
import java.io.IOException

class DetailsViewModel(private val liveData: MutableLiveData<DetailsFragmentAppState> = MutableLiveData<DetailsFragmentAppState>()) :
    ViewModel() {

    lateinit var repositoryLocationToOneWeather: RepositoryLocationToOneWeather
    lateinit var repositoryWeatherAddable: RepositoryWeatherAddable

    fun getLiveData(): MutableLiveData<DetailsFragmentAppState> {
        choiceRepository()
        return liveData
    }

    private fun choiceRepository() {
        if (isConnection()) {
            repositoryLocationToOneWeather = when (2) {
                1 -> {
                    RepositoryLocationToOneWeatherOkHttpImpl()
                }
                2 -> {
                    RepositoryLocationToOneWeatherRetrofitImpl()
                }
                3 -> {
                    RepositoryLocationToOneWeatherWeatherLoaderImpl()
                }
                4 -> {
                    RepositoryRoomImpl()
                }
                else -> {
                    RepositoryLocationToOneWeatherLocalImpl()
                }
            }
            repositoryWeatherAddable = when (0) {
                1 -> {
                    RepositoryRoomImpl()
                }
                else -> {
                    RepositoryRoomImpl()
                }
            }
        } else {
            repositoryLocationToOneWeather = when (1) {
                1 -> {
                    RepositoryRoomImpl()
                }
                2 -> {
                    RepositoryLocationToOneWeatherLocalImpl()
                }
                else -> {
                    RepositoryLocationToOneWeatherLocalImpl()
                }
            }
            repositoryWeatherAddable = when (0) {
                1 -> {
                    RepositoryRoomImpl()
                }
                else -> {
                    RepositoryRoomImpl()
                }
            }
        }
    }

    fun getWeather(weather: Weather) {
        liveData.value = DetailsFragmentAppState.Loading
        repositoryLocationToOneWeather.getWeather(weather, callback)
    }

    val callback = object : MyLargeSuperCallback {
        override fun onResponse(weather: Weather) {
            if (isConnection()) repositoryWeatherAddable.addWeather(weather)
            //если есть соед то добавим погоду
            Handler(Looper.getMainLooper()).post {
                liveData.postValue(DetailsFragmentAppState.Success(weather))
            }
        }

        override fun onFailure(e: IOException) {
            liveData.postValue(DetailsFragmentAppState.Error(e))
        }

    }

    fun isConnection(): Boolean {
        return true
    }
}
