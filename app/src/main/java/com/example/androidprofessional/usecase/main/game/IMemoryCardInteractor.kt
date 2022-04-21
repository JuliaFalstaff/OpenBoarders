package com.example.androidprofessional.usecase.main.game


interface IMemoryCardInteractor<AppState> {
    suspend fun getFavouritesData(): AppState
}