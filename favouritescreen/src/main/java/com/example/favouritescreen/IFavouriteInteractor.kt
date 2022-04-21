package com.example.favouritescreen

import com.example.module.data.DataModel

interface IFavouriteInteractor<AppState> {
    suspend fun getFavouritesData(): AppState
    suspend fun deleteFavouritesData(favWord: DataModel)
}