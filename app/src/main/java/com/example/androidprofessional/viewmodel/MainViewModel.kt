package com.example.androidprofessional.viewmodel

import androidx.lifecycle.LiveData
import com.example.androidprofessional.model.AppState
import com.example.androidprofessional.usecase.MainInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val interactor: MainInteractor) : BaseViewModel<AppState>() {

    override fun getData(word: String, isOnline: Boolean): LiveData<AppState> {
        liveDataForViewToObserve.postValue(AppState.Loading(null))
        viewModelCoroutineScope.launch { startInteractor(word, isOnline) }
        return super.getData(word, isOnline)
    }

    private suspend fun startInteractor(word: String, isOnline: Boolean) =
        withContext(Dispatchers.IO) {
            liveDataForViewToObserve.postValue(interactor.getData(word, isOnline))
        }

    override fun onCleared() {
        liveDataForViewToObserve.postValue(AppState.Success(null))
        super.onCleared()
    }

    override fun handleError(error: Throwable) {
        liveDataForViewToObserve.postValue(AppState.Error(error))
    }
}