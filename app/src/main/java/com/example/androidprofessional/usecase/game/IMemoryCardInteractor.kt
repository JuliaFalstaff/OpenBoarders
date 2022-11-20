package com.example.androidprofessional.usecase.game


interface IMemoryCardInteractor<AppState> {
    suspend fun getFavouritesData(): AppState
}