package com.example.repository.room.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import com.example.repository.room.entity.DataModelEntity

@Dao
interface DataModelDao {

    @Query("SELECT * FROM DataModelEntity")
    suspend fun all(): List<DataModelEntity>

    @Query("SELECT * FROM DataModelEntity WHERE word LIKE :word")
    suspend fun getDataByWord(word: String): DataModelEntity

    @Update
    suspend fun update(entity: DataModelEntity)
}