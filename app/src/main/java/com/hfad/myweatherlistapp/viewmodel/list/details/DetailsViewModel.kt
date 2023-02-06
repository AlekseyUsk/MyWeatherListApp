package com.hfad.myweatherlistapp.viewmodel.list.details


import android.content.Context
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hfad.myweatherlistapp.model.list.WeatherDTO
import com.hfad.myweatherlistapp.model.list.repository.RepositoryDetailsWeatherLoaderImpl
import com.hfad.myweatherlistapp.model.list.repository.retrofit.RepositoryDetailsRetrofitImpl
import com.hfad.myweatherlistapp.model.repository.MyLargeSuperCallback
import com.hfad.myweatherlistapp.model.repository.RepositoryDetails
import com.hfad.myweatherlistapp.model.repository.RepositoryDetailsLocalImpl
import com.hfad.myweatherlistapp.model.repository.RepositoryDetailsOkHttpImpl
import java.io.IOException

class DetailsViewModel(private val liveData: MutableLiveData<DetailsFragmentAppState> = MutableLiveData<DetailsFragmentAppState>(),) : ViewModel() {

    lateinit var repository: RepositoryDetails

    fun getLiveData(): MutableLiveData<DetailsFragmentAppState> {
        choiceRepository()
        return liveData
    }

   private fun choiceRepository() {
        repository = when (1) {
            1 -> {
                RepositoryDetailsOkHttpImpl()}
            2 -> { RepositoryDetailsRetrofitImpl()
            }
            3 -> { RepositoryDetailsWeatherLoaderImpl()}
            else -> { RepositoryDetailsLocalImpl()}
        }
    }

    fun getWeather(lat: Double, lon: Double) {
        choiceRepository()
        liveData.value = DetailsFragmentAppState.Loading
        repository.getWeather(lat,lon,callback)
    }

    val callback = object : MyLargeSuperCallback{
        override fun onResponse(weatherDTO: WeatherDTO) {
            Handler(Looper.getMainLooper()).post{
                liveData.postValue(DetailsFragmentAppState.Success(weatherDTO))
            }
        }

        override fun onFailure(e: IOException) {
        liveData.postValue(DetailsFragmentAppState.Error(e))
        }

    }


    fun isConnection(): Boolean {
        return false
    }
}
