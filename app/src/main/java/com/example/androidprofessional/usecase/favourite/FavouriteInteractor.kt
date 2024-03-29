package com.example.androidprofessional.usecase.favourite

import com.example.module.AppState
import com.example.module.data.DataModel
import com.example.repository.repository.IRepositoryLocal

class FavouriteInteractor(
    private val repositoryLocal: IRepositoryLocal<List<DataModel>>
) : IFavouriteInteractor<AppState> {

    override suspend fun getFavouritesData(): AppState {
        return AppState.Success(
            repositoryLocal.getFavouritesData()
        )
    }

    override suspend fun deleteFavouritesData(favWord: DataModel) {
        repositoryLocal.deleteFavouritesData(favWord)
    }
}