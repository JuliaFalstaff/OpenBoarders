package com.example.repository.room.dao

import androidx.room.*
import com.example.repository.room.entity.HistoryEntity

@Dao
interface HistoryDao {
    @Query("SELECT * FROM HistoryEntity")
    suspend fun getAll(): List<HistoryEntity>

    @Query("SELECT * FROM HistoryEntity WHERE word LIKE :word")
    suspend fun getDataByWord(word: String): HistoryEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: HistoryEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(entities: List<HistoryEntity>)

    @Update
    suspend fun update(entity: HistoryEntity)

    @Delete
    suspend fun delete(entity: HistoryEntity)
}