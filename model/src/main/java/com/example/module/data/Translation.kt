package com.example.module.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Translation(
        @SerializedName(TEXT_TRANSLATION) val translation: String? = "",
        @SerializedName(NOTE_TRANSLATION) val note: String? = "",
) : Parcelable {
    companion object {
        private const val TEXT_TRANSLATION = "text"
        private const val NOTE_TRANSLATION = "note"
    }
}