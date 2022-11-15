package com.example.androidprofessional.usecase.game

import com.example.module.AppState
import com.example.module.data.DataModel
import com.example.repository.repository.IRepositoryLocal

class MemoryCardsInteractor(
        private val repositoryLocal: IRepositoryLocal<List<DataModel>>,
) : IMemoryCardInteractor<AppState> {

    override suspend fun getFavouritesData(): AppState {
        return AppState.SuccessCard(
                repositoryLocal.getFavouritesData().random()
        )
    }
}