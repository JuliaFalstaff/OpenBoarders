package com.example.androidprofessional.model.data

import com.google.gson.annotations.SerializedName

class Translation(
        @field:SerializedName("text") val translation: String?,
        @field:SerializedName("note") val note: String?
)