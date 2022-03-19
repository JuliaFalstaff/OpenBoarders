package com.example.repository.datasource

import com.example.module.data.DataModel


interface IDataSourceLocal<T> : IDataSource<T> {
    suspend fun getData(): List<DataModel>
    suspend fun save(word: DataModel)
    suspend fun save(word: List<DataModel>)
    suspend fun getFavouritesData(): List<DataModel>
    suspend fun saveFavouritesData(favWord: DataModel)
    suspend fun deleteFavouritesData(favWord: DataModel)
}