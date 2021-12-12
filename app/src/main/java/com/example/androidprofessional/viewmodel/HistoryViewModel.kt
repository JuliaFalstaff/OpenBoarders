package com.example.androidprofessional.viewmodel

import androidx.lifecycle.LiveData
import com.example.androidprofessional.model.AppState
import com.example.androidprofessional.model.data.DataModel
import com.example.androidprofessional.usecase.history.HistoryInteractor
import com.example.androidprofessional.utils.parseLocalSearchResults
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HistoryViewModel(private val interactor: HistoryInteractor) :
    BaseViewModel<AppState>() {

    private var job: Job? = null

    override fun getData(word: String, isOnline: Boolean): LiveData<AppState> {
//        liveDataForViewToObserve.postValue(AppState.Loading(null))
//        job?.cancel()
//        job = viewModelCoroutineScope.launch { interactor.getAllHistory() }
        return super.getData(word, isOnline)
    }

    override fun onCleared() {
        liveDataForViewToObserve.postValue(AppState.SuccessHistoryData(null))
        super.onCleared()
    }

    override fun handleError(error: Throwable) {
        liveDataForViewToObserve.postValue(AppState.Error(error))
    }

    fun getAllHistory()  {
        liveDataForViewToObserve.postValue(AppState.Loading(null))
        job?.cancel()
        job = viewModelCoroutineScope.launch { interactor.getAllHistory() }
    }
}