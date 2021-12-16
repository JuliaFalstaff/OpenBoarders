package com.example.androidprofessional.usecase.main

import com.example.module.AppState
import com.example.module.data.DataModel
import com.example.repository.repository.IRepository
import com.example.repository.repository.IRepositoryLocal

class MainInteractor(
    val remoteRepository: IRepository<List<DataModel>>,
    val localRepository: IRepositoryLocal<List<DataModel>>,
) : com.example.core.Interactor<com.example.module.AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): com.example.module.AppState {
        val appState: com.example.module.AppState
        if (fromRemoteSource) {
            val data = remoteRepository.getData(word)
            localRepository.saveToDatabase(data)
            appState = com.example.module.AppState.Success(data.toMutableList())
        } else {
            appState = com.example.module.AppState.Success(localRepository.getData(word).toMutableList())
        }
        return appState
    }

    override suspend fun getData(word: String): com.example.module.AppState {
        TODO("Not yet implemented")
    }

    override suspend fun getHistoryData(): com.example.module.AppState {
      return com.example.module.AppState.Success(localRepository.getHistoryData().toMutableList())
    }

    override suspend fun getFavouritesData(): com.example.module.AppState {
        TODO("Not yet implemented")
    }

    override suspend fun saveFavouritesData(favWord: DataModel) {
        localRepository.saveFavouritesData(favWord)
    }
}