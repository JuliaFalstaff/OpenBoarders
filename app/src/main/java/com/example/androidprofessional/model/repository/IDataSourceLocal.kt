package com.example.androidprofessional.model.repository

import com.example.androidprofessional.model.AppState
import com.example.androidprofessional.model.data.DataModel
import javax.sql.DataSource

interface IDataSourceLocal<T>: IDataSource<T> {
    suspend fun saveToDB(appState: AppState)
}