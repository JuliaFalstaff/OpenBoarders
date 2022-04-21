package com.example.repository.repository

import com.example.module.data.DataModel
import com.example.repository.datasource.IDataSourceLocal

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

    override suspend fun deleteFavouritesData(favWord: DataModel) {
        dataSource.deleteFavouritesData(favWord)
    }
}