package com.example.androidprofessional.model.datasource

interface IDataSource<T> {
    suspend fun getData(word: String): T
}