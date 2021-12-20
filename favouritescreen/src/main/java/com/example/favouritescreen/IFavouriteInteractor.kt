package com.example.favouritescreen


interface IFavouriteInteractor<AppState> {
    suspend fun getFavouritesData(): AppState
}