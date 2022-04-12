package com.example.androidprofessional.usecase.main.game

import com.example.module.data.DataModel


interface IMemoryCardInteractor<AppState> {
    suspend fun getFavouritesData(): AppState
}