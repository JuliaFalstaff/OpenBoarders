package com.example.androidprofessional.model.repository

import com.example.androidprofessional.model.AppState
import com.example.androidprofessional.model.data.DataModel
import com.example.androidprofessional.model.datasource.IDataSourceLocal

class RepositoryImplLocal(private val dataSource: IDataSourceLocal<List<DataModel>>) :
    IRepositoryLocal<List<DataModel>> {
    override suspend fun saveToDB(searchWord: DataModel) {
        dataSource.saveToDB(searchWord)
    }

    override suspend fun getAllHistory(): MutableList<DataModel> {
        return dataSource.getAllHistory()
    }

    override suspend fun getData(word: String): List<DataModel> {
        return dataSource.getData(word)
    }

    override suspend fun saveToDBWord(searchWord: List<DataModel>) {
        dataSource.saveToDBWord(searchWord)
    }
}