package com.example.androidprofessional.model.data

import com.google.gson.annotations.SerializedName

class Translation(
        @SerializedName(TEXT_TRANSLATION) val translation: String?,
        @SerializedName(NOTE_TRANSLATION) val note: String?,
) {
    companion object {
        private const val TEXT_TRANSLATION = "text"
        private const val NOTE_TRANSLATION = "note"
    }
}