package com.example.repository.room.entity

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity
class TranslationEntity(
        @SerializedName(TEXT_TRANSLATION) val translation: String?,
        @SerializedName(NOTE_TRANSLATION) val note: String?,
) {
    companion object {
        private const val TEXT_TRANSLATION = "text"
        private const val NOTE_TRANSLATION = "note"
    }
}