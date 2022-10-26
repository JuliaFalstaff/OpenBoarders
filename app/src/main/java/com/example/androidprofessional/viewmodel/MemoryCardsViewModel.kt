package com.example.androidprofessional.viewmodel

import androidx.lifecycle.LiveData
import com.example.androidprofessional.usecase.main.game.MemoryCardsInteractor
import com.example.core.BaseViewModel
import com.example.module.AppState
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MemoryCardsViewModel(private val interactor: MemoryCardsInteractor) : BaseViewModel<AppState>() {

    private var job: Job? = null

    override fun getCardsLiveData(): LiveData<AppState> {
        job?.cancel()
        job = viewModelCoroutineScope.launch {
            liveDataForViewToObserve.postValue(interactor.getFavouritesData())
        }
        return super.getCardsLiveData()
    }

    override fun handleError(error: Throwable) {
        liveDataForViewToObserve.postValue(AppState.Error(error))
    }
}