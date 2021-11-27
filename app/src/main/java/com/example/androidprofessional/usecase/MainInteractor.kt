package com.example.androidprofessional.usecase

import com.example.androidprofessional.model.AppState
import com.example.androidprofessional.model.data.DataModel
import com.example.androidprofessional.view.Contract
import io.reactivex.Observable

class MainInteractor(
        private val remoteRepository: Contract.Repository<List<DataModel>>,
        private val localRepository: Contract.Repository<List<DataModel>>,
) : Contract.Interactor<AppState> {

    override fun getData(word: String, fromRemoteSource: Boolean): Observable<AppState> {
        return if (fromRemoteSource) {
            remoteRepository.getData(word).map { AppState.Success(it) }
        } else {
            localRepository.getData(word).map { AppState.Success(it) }
        }
    }
}