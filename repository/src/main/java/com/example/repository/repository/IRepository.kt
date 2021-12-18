package com.example.repository.repository


interface IRepository<T> {
    suspend fun getData(word: String): T
}