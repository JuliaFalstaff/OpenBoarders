package com.example.androidprofessional.model.data

import com.google.gson.annotations.SerializedName

class DataModel(
    @field:SerializedName("id") val id: Int,
    @field:SerializedName("text") val text: String?,
    @field:SerializedName("meanings") val meanings: List<Meanings>?
)