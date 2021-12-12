package com.example.androidprofessional.model.room

import com.example.androidprofessional.model.AppState
import com.example.androidprofessional.model.data.DataModel
import com.example.androidprofessional.model.repository.IDataSource
import com.example.androidprofessional.model.repository.IDataSourceLocal
import com.example.androidprofessional.model.room.dao.HistoryDao
import com.example.androidprofessional.utils.convertDataModelSuccessToEntity
import com.example.androidprofessional.utils.mapHistoryEntityToSearchResult
import io.reactivex.Observable

class RoomDataBaseImpl(private val historyDao: HistoryDao): IDataSourceLocal<List<DataModel>> {
    override suspend fun getData(word: String): List<DataModel> {
       return mapHistoryEntityToSearchResult(historyDao.getAll())
    }

    override suspend fun saveToDB(appState: AppState) {
        convertDataModelSuccessToEntity(appState)?.let {
            historyDao.insert(it)
        }
    }
}