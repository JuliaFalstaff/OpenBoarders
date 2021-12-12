package com.example.androidprofessional.model.datasource

import com.example.androidprofessional.model.data.DataModel

interface IDataSourceLocal<T> : IDataSource<T> {
    suspend fun getData(): List<DataModel>
    suspend fun save(word: DataModel)
    suspend fun save(word: List<DataModel>)
    suspend fun getFavouritesData(): List<DataModel>
    suspend fun saveFavouritesData(favWord: DataModel)
}