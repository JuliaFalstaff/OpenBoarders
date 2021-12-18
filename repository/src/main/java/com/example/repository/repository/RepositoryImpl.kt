package com.example.repository.repository

import com.example.module.data.DataModel
import com.example.repository.datasource.IDataSource

class RepositoryImpl(private val dataSource: IDataSource<List<DataModel>>) :
    IRepository<List<DataModel>> {
    override suspend fun getData(word: String): List<DataModel> {
        return dataSource.getData(word)
    }
}