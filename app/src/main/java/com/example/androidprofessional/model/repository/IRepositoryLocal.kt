package com.example.androidprofessional.model.repository

import com.example.androidprofessional.model.data.DataModel

interface IRepositoryLocal<T> : IRepository<T> {
    suspend fun getHistoryData(): List<DataModel>
    suspend fun saveToDatabase(word: List<DataModel>)
    suspend fun getFavouritesData(): List<DataModel>
    suspend fun saveFavouritesData(favWord: DataModel)
}