package com.example.androidprofessional.utils

import android.content.Context
import androidx.room.Room
import com.google.android.exoplayer2.ExoPlayer

object ExoPlayerProvider {
    private var instance: ExoPlayer? = null

    fun getInstance() =
        instance ?: throw RuntimeException("ExoPlayer has no created, please create")

    fun create(context: Context?) {
        if (instance == null) {
            instance = context?.let { ExoPlayer.Builder(it).build() }
        }
    }
}