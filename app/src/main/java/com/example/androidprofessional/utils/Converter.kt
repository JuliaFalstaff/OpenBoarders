package com.example.androidprofessional.utils

import androidx.room.TypeConverter
import com.example.androidprofessional.model.data.Meanings
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converter {
    private val gson = Gson()

    @TypeConverter
    fun toJson(segments: List<Meanings>?): String {
        return gson.toJson(segments)
    }

    @TypeConverter
    fun fromJson(json: String?): List<Meanings> {
        return gson.fromJson<List<Meanings>>(
            json,
            object : TypeToken<List<Meanings>?>() {}.type
        )
    }
}