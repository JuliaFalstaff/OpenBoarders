package com.example.androidprofessional.usecase

interface Interactor<T> {
    suspend fun getData(word: String, fromRemoteSource: Boolean): T
}