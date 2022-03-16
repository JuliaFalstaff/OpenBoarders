package com.example.repository.room.entity


import androidx.room.Embedded
import androidx.room.Entity
import com.google.gson.annotations.SerializedName


@Entity
data class MeaningsEntity(
        @SerializedName(ID_MEANINGS) val id: Int,
        @Embedded
        @SerializedName(TRANSLATION_MEANINGS) val translation: TranslationEntity? ,
        @SerializedName(IMAGE_URL_MEANINGS) val imageUrl: String?,
        @SerializedName(TRANSCRIPTION_MEANINGS) val transcription: String? ,
        @SerializedName(SOUND_URL_MEANINGS) val soundUrl: String? ,
        @SerializedName(MNEMONICS) val mnemonics: String?
) {
    companion object {
        private const val ID_MEANINGS = "id"
        private const val TRANSLATION_MEANINGS = "translation"
        private const val IMAGE_URL_MEANINGS = "imageUrl"
        private const val TRANSCRIPTION_MEANINGS = "transcription"
        private const val SOUND_URL_MEANINGS = "soundUrl"
        private const val MNEMONICS = "mnemonics"
    }
}


