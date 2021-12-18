package com.example.historyscreen

import com.example.module.AppState
import com.example.module.data.DataModel
import com.example.repository.repository.IRepository
import com.example.repository.repository.IRepositoryLocal

class HistoryInteractor(
    private val repositoryRemote: IRepository<List<DataModel>>,
    private val repositoryLocal: IRepositoryLocal<List<DataModel>>
) : com.example.core.Interactor<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        return AppState.Success(
            if (fromRemoteSource) {
                repositoryRemote
            } else {
                repositoryLocal
            }.getData(word).toMutableList()
        )
    }

    override suspend fun getData(word: String): AppState {
        return AppState.Success(repositoryRemote.getData(word).toMutableList())
    }

    override suspend fun getHistoryData(): AppState {
        return AppState.Success(repositoryLocal.getHistoryData().toMutableList())
    }

    override suspend fun getFavouritesData(): AppState {
        TODO("Not yet implemented")
    }

    override suspend fun saveFavouritesData(favWord: DataModel) {
        TODO("Not yet implemented")
    }
}