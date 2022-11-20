package com.example.androidprofessional.ui.favourite

import androidx.lifecycle.LiveData
import com.example.androidprofessional.usecase.favourite.FavouriteInteractor
import com.example.core.BaseViewModel
import com.example.module.AppState
import com.example.module.data.DataModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class FavouriteViewModel(private val interactor: FavouriteInteractor) : BaseViewModel<AppState>() {

    private var job: Job? = null

    override fun handleError(error: Throwable) {
        liveDataForViewToObserve.postValue(AppState.Error(error))
    }

    override fun getFavouriteData(): LiveData<AppState> {
        liveDataForViewToObserve.postValue(AppState.Loading)
        job?.cancel()
        job = viewModelCoroutineScope.launch {
            liveDataForViewToObserve.postValue(interactor.getFavouritesData())
        }
        return super.getFavouriteData()
    }

    fun deleteFavouriteWord(word: DataModel) {
        viewModelCoroutineScope.launch {
            interactor.deleteFavouritesData(word)
        }
    }

    override fun onCleared() {
        liveDataForViewToObserve.postValue(AppState.Success(null))
        super.onCleared()
    }
}