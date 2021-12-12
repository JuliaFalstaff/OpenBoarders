package com.example.androidprofessional.model.repository

import com.example.androidprofessional.model.AppState
import com.example.androidprofessional.model.data.DataModel
import com.example.androidprofessional.model.datasource.IDataSourceLocal

class RepositoryImplLocal(private val dataSource: IDataSourceLocal<List<DataModel>>) :
    IRepositoryLocal<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        return dataSource.getData(word)
    }

    override suspend fun getHistoryData(): List<DataModel> {
        return dataSource.getData()
    }

    override suspend fun saveToDatabase(word: List<DataModel>) {
        dataSource.save(word)
    }

    override suspend fun getFavouritesData(): List<DataModel> {
        return dataSource.getFavouritesData()
    }

    override suspend fun saveFavouritesData(favWord: DataModel) {
        dataSource.saveFavouritesData(favWord)
    }
}