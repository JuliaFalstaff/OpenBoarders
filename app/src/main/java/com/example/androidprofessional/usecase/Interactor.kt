package com.example.androidprofessional.usecase

import com.example.androidprofessional.model.data.DataModel


interface Interactor<T> {
    suspend fun getData(word: String, fromRemoteSource: Boolean): T
    suspend fun saveToDB(searchWord: DataModel)
    suspend fun getAllHistory(): T

}