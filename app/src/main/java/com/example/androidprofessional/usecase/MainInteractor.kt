package com.example.androidprofessional.usecase

import com.example.androidprofessional.model.AppState
import com.example.androidprofessional.model.data.DataModel
import com.example.androidprofessional.model.repository.IRepository
import io.reactivex.Observable

class MainInteractor(
        val remoteRepository: IRepository<List<DataModel>>,
        val localRepository: IRepository<List<DataModel>>,
) : Interactor<AppState> {

    override fun getData(word: String, fromRemoteSource: Boolean): Observable<AppState> {
        return if (fromRemoteSource) {
            remoteRepository.getData(word).map { AppState.Success(it.toMutableList()) }
        } else {
            localRepository.getData(word).map { AppState.Success(it.toMutableList()) }
        }
    }
}