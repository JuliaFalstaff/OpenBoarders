package com.example.androidprofessional.usecase.history

import com.example.module.AppState
import com.example.module.data.DataModel
import com.example.repository.repository.IRepository
import com.example.repository.repository.IRepositoryLocal

class HistoryInteractor(
    private val repositoryRemote: IRepository<List<DataModel>>,
    private val repositoryLocal: IRepositoryLocal<List<DataModel>>
) : com.example.core.Interactor<com.example.module.AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): com.example.module.AppState {
        return com.example.module.AppState.Success(
            if (fromRemoteSource) {
                repositoryRemote
            } else {
                repositoryLocal
            }.getData(word).toMutableList()
        )
    }

    override suspend fun getData(word: String): com.example.module.AppState {
        return com.example.module.AppState.Success(repositoryRemote.getData(word).toMutableList())
    }

    override suspend fun getHistoryData(): com.example.module.AppState {
        return com.example.module.AppState.Success(repositoryLocal.getHistoryData().toMutableList())
    }

    override suspend fun getFavouritesData(): com.example.module.AppState {
        TODO("Not yet implemented")
    }

    override suspend fun saveFavouritesData(favWord: DataModel) {
        TODO("Not yet implemented")
    }
}