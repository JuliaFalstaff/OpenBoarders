package com.example.androidprofessional.model.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.androidprofessional.model.room.dao.HistoryDao
import com.example.androidprofessional.model.room.entity.HistoryEntity
import com.example.androidprofessional.utils.Converter

@Database(entities = [HistoryEntity::class], version = 1, exportSchema = true)
@TypeConverters(Converter::class)
abstract class TranslatorDataBase : RoomDatabase() {
    abstract fun historyDao(): HistoryDao
}