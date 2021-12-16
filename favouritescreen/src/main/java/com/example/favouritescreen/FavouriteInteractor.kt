package com.example.favouritescreen

import com.example.module.data.DataModel
import com.example.repository.repository.IRepositoryLocal

class FavouriteInteractor(
    private val repositoryLocal: IRepositoryLocal<List<DataModel>>
) : com.example.core.Interactor<com.example.module.AppState> {
    override suspend fun getData(
        word: String,
        fromRemoteSource: Boolean
    ): com.example.module.AppState {
        TODO("Not yet implemented")
    }

    override suspend fun getData(word: String): com.example.module.AppState {
        TODO("Not yet implemented")
    }

    override suspend fun getHistoryData(): com.example.module.AppState {
        TODO("Not yet implemented")
    }

    override suspend fun getFavouritesData(): com.example.module.AppState {
        return com.example.module.AppState.Success(
            repositoryLocal.getFavouritesData().toMutableList()
        )
    }

    override suspend fun saveFavouritesData(favWord: DataModel) {
        repositoryLocal.saveFavouritesData(favWord)
    }
}