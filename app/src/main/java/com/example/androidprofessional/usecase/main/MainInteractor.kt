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
            val data = remoteRepository.getData(word)
            localRepository.saveToDatabase(data)
            appState = AppState.Success(data.toMutableList())
        } else {
            appState = AppState.Success(localRepository.getData(word).toMutableList())
        }
        return appState
    }

    override suspend fun getData(word: String): AppState {
        TODO("Not yet implemented")
    }

    override suspend fun getHistoryData(): AppState {
      return AppState.Success(localRepository.getHistoryData().toMutableList())
    }

    override suspend fun getFavouritesData(): AppState {
        TODO("Not yet implemented")
    }

    override suspend fun saveFavouritesData(favWord: DataModel) {
        localRepository.saveFavouritesData(favWord)
    }
}