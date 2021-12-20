package com.example.module.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
class DataModel(
        @SerializedName(ID_DATA_MODEL) val id: Int? = 0,
        @SerializedName(TEXT_DATA_MODEL) val text: String? = "",
        @SerializedName(MEANINGS_MODEL) val meanings: List<Meanings>? = null,
) : Parcelable {
    companion object {
        private const val ID_DATA_MODEL = "id"
        private const val TEXT_DATA_MODEL = "text"
        private const val MEANINGS_MODEL = "meanings"
    }
}