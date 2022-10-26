package com.example.repository.datasource

import com.example.module.data.DataModel
import com.example.repository.*
import com.example.repository.room.dao.FavouriteDao
import com.example.repository.room.dao.HistoryDao

class RoomDataBaseImpl(private val historyDao: HistoryDao, private val favouriteDao: FavouriteDao) : IDataSourceLocal<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        return mapHistoryEntityToSearchResult(historyDao.getAll())
    }

    override suspend fun getData(): List<DataModel> {
        return historyDao.getAll().let { data ->
            data.flatMap { entity ->
                convertFromEntityToData(entity)
            }
        }
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

    override suspend fun getFavouritesData(): List<DataModel> {
        return favouriteDao.getAll().let { data ->
            data.flatMap { entity ->
                convertFromFavEntityToData(entity)
            }
        }
    }

    override suspend fun saveFavouritesData(favWord: DataModel) {
        val data = convertFromDataToFavEntity(favWord)
        favouriteDao.insert(data)
    }

    override suspend fun deleteFavouritesData(favWord: DataModel) {
        val data = convertFromDataToFavEntity(favWord)
        favouriteDao.delete(data)
    }
}