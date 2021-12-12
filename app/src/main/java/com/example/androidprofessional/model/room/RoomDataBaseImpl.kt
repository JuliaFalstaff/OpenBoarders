package com.example.androidprofessional.model.room

import com.example.androidprofessional.model.data.DataModel
import com.example.androidprofessional.model.datasource.IDataSourceLocal
import com.example.androidprofessional.model.room.dao.HistoryDao
import com.example.androidprofessional.utils.convertDataModelToHistoryEntity
import com.example.androidprofessional.utils.convertDataModelToListHistoryEntity
import com.example.androidprofessional.utils.mapHistoryEntityToSearchResult

class RoomDataBaseImpl(private val historyDao: HistoryDao) : IDataSourceLocal<List<DataModel>> {
    override suspend fun saveToDB(searchWord: DataModel) {
        convertDataModelToHistoryEntity(searchWord)?.let {
            historyDao.insert(it)
        }
    }

    override suspend fun getAllHistory(): MutableList<DataModel> {
        return mapHistoryEntityToSearchResult(historyDao.getAll())
    }

    override suspend fun getData(word: String): List<DataModel> {
        TODO("Not yet implemented")
    }

    override suspend fun saveToDBWord(searchWord: List<DataModel>) {
        convertDataModelToListHistoryEntity(searchWord)?.let {
            historyDao.insertAll(it)
        }
    }
}