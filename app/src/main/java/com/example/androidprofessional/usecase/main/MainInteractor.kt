package com.example.androidprofessional.usecase.main

import com.example.androidprofessional.model.AppState
import com.example.androidprofessional.model.data.DataModel
import com.example.androidprofessional.model.repository.IRepository
import com.example.androidprofessional.model.repository.IRepositoryLocal
import com.example.androidprofessional.usecase.Interactor

class MainInteractor(
    val remoteRepository: IRepository<List<DataModel>>,
    val localRepository: IRepositoryLocal<List<DataModel>>,
) : Interactor<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        val appState: AppState
        if (fromRemoteSource) {
            appState = AppState.Success(remoteRepository.getData(word).toMutableList())
            localRepository.saveToDB(appState)
        } else {
            appState = AppState.Success(localRepository.getData(word).toMutableList())
        }
        return appState
    }
}