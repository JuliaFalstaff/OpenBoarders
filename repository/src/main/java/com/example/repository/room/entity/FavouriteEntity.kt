package com.example.repository.room.entity

import androidx.room.*
import com.example.module.data.Meanings
import com.example.utils.Converter

@Entity(indices = arrayOf(Index(value = arrayOf("word"), unique = true)))
data class FavouriteEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int?,
    @ColumnInfo(name = "word") val word: String?,
    @TypeConverters(Converter::class)
    @ColumnInfo(name = "meanings") val meanings: List<Meanings>?
)