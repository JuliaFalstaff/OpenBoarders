package com.example.androidprofessional.model.datasource

import com.example.androidprofessional.model.data.DataModel
import com.example.androidprofessional.model.room.dao.HistoryDao
import com.example.androidprofessional.model.room.entity.HistoryEntity
import com.example.androidprofessional.utils.convertFromDataToEntity
import com.example.androidprofessional.utils.convertFromEntityToData
import com.example.androidprofessional.utils.mapHistoryEntityToSearchResult

class RoomDataBaseImpl(private val historyDao: HistoryDao) : IDataSourceLocal<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        return mapHistoryEntityToSearchResult(historyDao.getAll())
    }

    override suspend fun getData(): List<DataModel> {
        val dataList = historyDao.getAll().let { data ->
            data.flatMap { entity ->
                convertFromEntityToData(entity)
            }

        }
        return dataList
    }

    override suspend fun save(word: DataModel) {
        val data = convertFromDataToEntity(word)
        historyDao.insert(data)
    }

    override suspend fun save(word: List<DataModel>) {
        val data = word.flatMap {
            listOf(convertFromDataToEntity(it))
        }
        historyDao.insertAll(data)
    }
}