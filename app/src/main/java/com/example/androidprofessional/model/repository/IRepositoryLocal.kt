package com.example.androidprofessional.model.repository

import com.example.androidprofessional.model.AppState
import com.example.androidprofessional.model.data.DataModel

interface IRepositoryLocal<T>: IRepository<T> {
    suspend fun saveToDB(appState: AppState)
}