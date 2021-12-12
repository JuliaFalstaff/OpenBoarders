package com.example.androidprofessional.usecase

import com.example.androidprofessional.model.AppState
import com.example.androidprofessional.model.data.DataModel
import com.example.androidprofessional.model.repository.IRepository
import com.example.androidprofessional.model.repository.IRepositoryLocal

class FavouriteInteractor (
private val repositoryLocal: IRepositoryLocal<List<DataModel>>
) : Interactor<AppState> {
    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        TODO("Not yet implemented")
    }

    override suspend fun getData(word: String): AppState {
        TODO("Not yet implemented")
    }

    override suspend fun getHistoryData(): AppState {
        TODO("Not yet implemented")
    }

    override suspend fun getFavouritesData(): AppState {
       return AppState.Success(repositoryLocal.getFavouritesData().toMutableList())
    }

    override suspend fun saveFavouritesData(favWord: DataModel) {
        repositoryLocal.saveFavouritesData(favWord)
    }
}