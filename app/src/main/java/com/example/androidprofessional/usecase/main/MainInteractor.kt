package com.example.androidprofessional.usecase.main

import com.example.module.AppState
import com.example.module.data.DataModel
import com.example.repository.repository.IRepository
import com.example.repository.repository.IRepositoryLocal

class MainInteractor(
    val remoteRepository: IRepository<List<DataModel>>,
    val localRepository: IRepositoryLocal<List<DataModel>>,
) : com.example.core.Interactor<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        val appState: AppState
        if (fromRemoteSource) {
            val data = remoteRepository.getData(word)
            localRepository.saveToDatabase(data)
            appState = AppState.Success(data)
        } else {
            appState = AppState.Success(localRepository.getData(word))
        }
        return appState
    }

    override suspend fun getHistoryData(): AppState {
      return AppState.Success(localRepository.getHistoryData())
    }

    override suspend fun saveFavouritesData(favWord: DataModel) {
        localRepository.saveFavouritesData(favWord)
    }

    override suspend fun deleteFavouritesData(favWord: DataModel) {
        localRepository.deleteFavouritesData(favWord)
    }
}