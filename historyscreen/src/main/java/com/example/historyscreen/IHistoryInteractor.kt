package com.example.historyscreen

import com.example.core.Interactor
import com.example.module.AppState


interface IHistoryInteractor<AppState> {
    suspend fun getData(word: String): AppState
    suspend fun getData(word: String, fromRemoteSource: Boolean): AppState
    suspend fun getHistoryData(): com.example.module.AppState
}