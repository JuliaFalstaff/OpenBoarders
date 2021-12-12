package com.example.androidprofessional.model.room.dao

import androidx.room.*
import com.example.androidprofessional.model.room.entity.FavouriteEntity
import com.example.androidprofessional.model.room.entity.HistoryEntity

@Dao
interface FavouriteDao {
    @Query("SELECT * FROM FavouriteEntity")
    suspend fun getAll(): List<FavouriteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: FavouriteEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(entities: List<FavouriteEntity>)

    @Update
    suspend fun update(entity: FavouriteEntity)

    @Delete
    suspend fun delete(entity: FavouriteEntity)
}