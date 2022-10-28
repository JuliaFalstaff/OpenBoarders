package com.example.androidprofessional.history


interface IHistoryInteractor<AppState> {
    suspend fun getData(word: String): AppState
    suspend fun getData(word: String, fromRemoteSource: Boolean): AppState
    suspend fun getHistoryData(): com.example.module.AppState
}