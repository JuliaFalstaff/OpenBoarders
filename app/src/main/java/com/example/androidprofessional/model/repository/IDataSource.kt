package com.example.androidprofessional.model.repository

interface IDataSource<T> {
    suspend fun getData(word: String): T
}