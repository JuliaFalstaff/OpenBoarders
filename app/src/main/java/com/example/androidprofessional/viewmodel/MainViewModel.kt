package com.example.androidprofessional.viewmodel

import androidx.lifecycle.LiveData
import com.example.androidprofessional.usecase.main.MainInteractor
import com.example.module.data.DataModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainViewModel(private val interactor: MainInteractor) :
    com.example.core.BaseViewModel<com.example.module.AppState>() {

    private var job: Job? = null

    override fun getData(word: String, isOnline: Boolean): LiveData<com.example.module.AppState> {
        liveDataForViewToObserve.postValue(com.example.module.AppState.Loading(null))
        job?.cancel()
        job = viewModelCoroutineScope.launch { startInteractor(word, isOnline) }
        return super.getData(word, isOnline)
    }

    private suspend fun startInteractor(word: String, isOnline: Boolean) {
        liveDataForViewToObserve.postValue(interactor.getData(word, isOnline))
    }

    override fun onCleared() {
        liveDataForViewToObserve.postValue(com.example.module.AppState.Success(null))
        super.onCleared()
    }

    override fun handleError(error: Throwable) {
        liveDataForViewToObserve.postValue(com.example.module.AppState.Error(error))
    }

    fun saveToFav(word: DataModel) {
        viewModelCoroutineScope.launch { interactor.saveFavouritesData(word) }
    }
}