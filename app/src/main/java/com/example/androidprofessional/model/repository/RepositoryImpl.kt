package com.example.androidprofessional.model.repository

import com.example.androidprofessional.model.data.DataModel

class RepositoryImpl(private val dataSource: IDataSource<List<DataModel>>) :
    IRepository<List<DataModel>> {
    override suspend fun getData(word: String): List<DataModel> {
        return dataSource.getData(word)
    }
}