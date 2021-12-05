package com.example.androidprofessional.usecase

import com.example.androidprofessional.model.AppState
import com.example.androidprofessional.model.data.DataModel
import com.example.androidprofessional.model.repository.IRepository

class MainInteractor(
    val remoteRepository: IRepository<List<DataModel>>,
    val localRepository: IRepository<List<DataModel>>,
) : Interactor<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        return AppState.Success(
            if (fromRemoteSource) {
                remoteRepository
            } else {
                localRepository
            }.getData(word).toMutableList()
        )
    }
}