package com.example.repository.room.entity

import androidx.room.*
import com.example.module.data.Meanings
import com.example.repository.room.entity.FavouriteEntity.Companion.ENTITY_INDEX_VALUE
import com.example.utils.Converter

@Entity(indices = arrayOf(Index(value = arrayOf(ENTITY_INDEX_VALUE), unique = true)))
data class FavouriteEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = FAVOURITE_ENTITY_ID) val id: Int?,
    @ColumnInfo(name = FAVOURITE_ENTITY_WORD) val word: String?,
    @TypeConverters(Converter::class)
    @ColumnInfo(name = FAVOURITE_ENTITY_MEANINGS) val meanings: List<Meanings>?
) {
    companion object {
        const val ENTITY_INDEX_VALUE = "word"
        const val FAVOURITE_ENTITY_ID = "id"
        const val FAVOURITE_ENTITY_WORD = "word"
        const val FAVOURITE_ENTITY_MEANINGS = "meanings"
    }
}