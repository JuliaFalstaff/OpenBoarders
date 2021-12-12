package com.example.androidprofessional.usecase

import com.example.androidprofessional.model.AppState
import com.example.androidprofessional.model.data.DataModel


interface Interactor<T> {
    suspend fun getData(word: String, fromRemoteSource: Boolean): T
    suspend fun getData(word: String): AppState
    suspend fun getHistoryData(): AppState
    suspend fun getFavouritesData(): AppState
    suspend fun saveFavouritesData(favWord: DataModel)
}