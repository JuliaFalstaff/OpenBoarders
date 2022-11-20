package com.example.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.module.AppState
import kotlinx.coroutines.*

abstract class BaseViewModel<T : AppState>(
    protected val liveDataForViewToObserve: MutableLiveData<T> = MutableLiveData(),
) : ViewModel() {

    protected val viewModelCoroutineScope = CoroutineScope(
        Dispatchers.IO
                + SupervisorJob()
                + CoroutineExceptionHandler { _, throwable ->
            handleError(throwable)
        })

    open fun getData(word: String, isOnline: Boolean): LiveData<T> = liveDataForViewToObserve

    open fun getHistoryData(): LiveData<T> = liveDataForViewToObserve

    open fun getFavouriteData(): LiveData<T> = liveDataForViewToObserve

    open fun getCardsLiveData(): LiveData<T> = liveDataForViewToObserve

    override fun onCleared() {
        super.onCleared()
        cancelJob()
    }

    private fun cancelJob() {
        viewModelCoroutineScope.cancel()
    }

    abstract fun handleError(error: Throwable)
}