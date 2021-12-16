package com.example.repository.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.repository.room.dao.FavouriteDao
import com.example.repository.room.dao.HistoryDao
import com.example.repository.room.entity.FavouriteEntity
import com.example.repository.room.entity.HistoryEntity
import com.example.utils.Converter

@Database(
    entities = [HistoryEntity::class, FavouriteEntity::class],
    version = 2,
    exportSchema = true
)
@TypeConverters(Converter::class)
abstract class TranslatorDataBase : RoomDatabase() {
    abstract fun historyDao(): HistoryDao
    abstract fun favouriteDao(): FavouriteDao
}