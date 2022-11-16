package com.example.repository.room.entity

import androidx.room.*
import com.example.module.data.Meanings
import com.example.repository.room.entity.DataModelEntity.Companion.ENTITY_INDEX_VALUE
import com.example.utils.Converter

@Entity(indices = arrayOf(Index(value = arrayOf(ENTITY_INDEX_VALUE), unique = true)))
data class DataModelEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ENTITY_ID) val id: Int?,
    @ColumnInfo(name = ENTITY_WORD) val word: String?,
    @TypeConverters(Converter::class)
    @ColumnInfo(name = ENTITY_MEANINGS) val meanings: List<Meanings>?
) {
    companion object {
        const val ENTITY_INDEX_VALUE = "word"
        const val ENTITY_ID = "id"
        const val ENTITY_WORD = "word"
        const val ENTITY_MEANINGS = "meanings"
    }
}