package com.example.androidprofessional.model.repository


interface IRepository<T> {
    suspend fun getData(word: String): T
}