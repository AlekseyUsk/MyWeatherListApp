package com.hfad.myweatherlistapp.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hfad.myweatherlistapp.repository.*

class WeatherListViewModel(
    private val liveData: MutableLiveData<AppState> = MutableLiveData<AppState>(),
) :
    ViewModel() {

    lateinit var repositoryMultipleWeatherQuery: RepositoryMultipleWeatherQuery
    lateinit var repositoryOneWeather: RepositoryOneWeather

    fun getLiveData(): MutableLiveData<AppState> {
        choiceRepository()
        return liveData
    }

    private fun choiceRepository() {   // действия
        if (isConnection()) {
            repositoryOneWeather = RepositoryRemoteImpl()
        } else {
            RepositoryLocalImpl()
        }
        repositoryMultipleWeatherQuery = RepositoryLocalImpl()
    }

    private fun sentRequest(location: Location) {
        // choiceRepository()
//        liveData.value = AppState.Loading
//        Thread {
//            Thread.sleep(300L)
//            if ((0..3).random(Random(System.currentTimeMillis()))==10) {
//                liveData.postValue(AppState.Error(throw IllegalStateException("Что то пошло не так...")))
//            } else {
                liveData.postValue(
                    AppState.SuccessMulti(
                        repositoryMultipleWeatherQuery.getListWeather(
                            location
                        )
                    )
                )
            }
     //   }
  //  }

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
