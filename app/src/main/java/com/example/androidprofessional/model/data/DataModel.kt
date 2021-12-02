package com.example.androidprofessional.model.data

import com.google.gson.annotations.SerializedName

class DataModel(
        @SerializedName(ID_DATA_MODEL) val id: Int,
        @SerializedName(TEXT_DATA_MODEL) val text: String?,
        @SerializedName(MEANINGS_MODEL) val meanings: List<Meanings>?,
) {
    companion object {
        private const val ID_DATA_MODEL = "id"
        private const val TEXT_DATA_MODEL = "text"
        private const val MEANINGS_MODEL = "meanings"
    }
}