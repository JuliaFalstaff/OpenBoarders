package com.example.androidprofessional.viewmodel

import androidx.lifecycle.LiveData
import com.example.androidprofessional.model.AppState
import com.example.androidprofessional.model.data.DataModel
import com.example.androidprofessional.usecase.main.MainInteractor
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainViewModel(private val interactor: MainInteractor) : BaseViewModel<AppState>() {

    private var job: Job? = null

    override fun getData(word: String, isOnline: Boolean): LiveData<AppState> {
        liveDataForViewToObserve.postValue(AppState.Loading(null))
        job?.cancel()
        job = viewModelCoroutineScope.launch { startInteractor(word, isOnline) }
        return super.getData(word, isOnline)
    }

    private suspend fun startInteractor(word: String, isOnline: Boolean) {
        liveDataForViewToObserve.postValue(interactor.getData(word, isOnline))
    }

    override fun onCleared() {
        liveDataForViewToObserve.postValue(AppState.Success(null))
        super.onCleared()
    }

    override fun handleError(error: Throwable) {
        liveDataForViewToObserve.postValue(AppState.Error(error))
    }

    fun saveToFav(word: DataModel) {
        viewModelCoroutineScope.launch { interactor.saveFavouritesData(word) }
    }
}