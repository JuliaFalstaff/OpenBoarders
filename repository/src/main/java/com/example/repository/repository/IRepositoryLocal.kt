package com.example.repository.repository

import com.example.module.data.DataModel

interface IRepositoryLocal<T> : IRepository<T> {
    suspend fun getHistoryData(): List<DataModel>
    suspend fun saveToDatabase(word: List<DataModel>)
    suspend fun getFavouritesData(): List<DataModel>
    suspend fun saveFavouritesData(favWord: DataModel)
    suspend fun deleteFavouritesData(favWord: DataModel)
}