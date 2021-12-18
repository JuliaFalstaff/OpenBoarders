package com.example.core

import com.example.module.AppState
import com.example.module.data.DataModel


interface Interactor<T> {
    suspend fun getData(word: String, fromRemoteSource: Boolean): T
    suspend fun getData(word: String): AppState
    suspend fun getHistoryData(): AppState
    suspend fun getFavouritesData(): AppState
    suspend fun saveFavouritesData(favWord: DataModel)
}