package com.example.androidprofessional.model.room.entity

import androidx.room.*
import com.example.androidprofessional.model.data.Meanings
import com.example.androidprofessional.utils.Converter

@Entity(indices = arrayOf(Index(value = arrayOf("word"), unique = true)))
data class HistoryEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int?,
    @ColumnInfo(name = "word") val word: String?,
    @TypeConverters(Converter::class)
    @ColumnInfo(name = "meanings") val meanings: List<Meanings>?
)