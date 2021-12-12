package com.example.androidprofessional.model.repository

import com.example.androidprofessional.model.AppState
import com.example.androidprofessional.model.data.DataModel
import com.example.androidprofessional.model.room.RoomDataBaseImpl
import com.example.androidprofessional.utils.convertDataModelSuccessToEntity

class RepositoryImplLocal(private val dataSource: IDataSourceLocal<List<DataModel>> ) :
    IRepositoryLocal<List<DataModel>> {
    override suspend fun saveToDB(appState: AppState) {
        dataSource.saveToDB(appState)
    }

    override suspend fun getData(word: String): List<DataModel> {
        return dataSource.getData(word)
    }
}