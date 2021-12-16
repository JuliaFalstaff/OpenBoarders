package com.example.repository.datasource

interface IDataSource<T> {
    suspend fun getData(word: String): T
}