package com.example.androidprofessional.model.datasource

import com.example.androidprofessional.model.AppState
import com.example.androidprofessional.model.data.DataModel

interface IDataSourceLocal<T>: IDataSource<T> {
    suspend fun saveToDB(searchWord: DataModel)
    suspend fun saveToDBWord(searchWord: List<DataModel>)
    suspend fun getAllHistory(): MutableList<DataModel>
}