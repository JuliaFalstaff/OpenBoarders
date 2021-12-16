package com.example.repository.room.entity

import androidx.room.*
import com.example.module.data.Meanings
import com.example.repository.room.entity.HistoryEntity.Companion.ENTITY_INDEX_VALUE
import com.example.utils.Converter

@Entity(indices = arrayOf(Index(value = arrayOf(ENTITY_INDEX_VALUE), unique = true)))
data class HistoryEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = HISTORY_ENTITY_ID) val id: Int?,
    @ColumnInfo(name = HISTORY_ENTITY_WORD) val word: String?,
    @TypeConverters(Converter::class)
    @ColumnInfo(name = HISTORY_ENTITY_MEANINGS) val meanings: List<Meanings>?
) {
    companion object {
        const val ENTITY_INDEX_VALUE = "word"
        const val HISTORY_ENTITY_ID = "id"
        const val HISTORY_ENTITY_WORD = "word"
        const val HISTORY_ENTITY_MEANINGS = "meanings"
    }
}