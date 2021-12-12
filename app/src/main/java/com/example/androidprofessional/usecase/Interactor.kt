package com.example.androidprofessional.usecase

import com.example.androidprofessional.model.AppState


interface Interactor<T> {
    suspend fun getData(word: String, fromRemoteSource: Boolean): T
    suspend fun getData(word: String): AppState
    suspend fun getHistoryData(): AppState
}