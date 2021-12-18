package com.example.androidprofessional.di.koin

import androidx.room.Room
import com.example.androidprofessional.di.DATABASE_NAME
import com.example.module.data.DataModel
import com.example.repository.repository.*
import com.example.repository.retrofit.RetrofitImpl
import com.example.repository.datasource.RoomDataBaseImpl
import com.example.repository.room.TranslatorDataBase
import com.example.favouritescreen.FavouriteInteractor
import com.example.androidprofessional.usecase.main.MainInteractor
import com.example.favouritescreen.FavouriteViewModel
import com.example.androidprofessional.viewmodel.MainViewModel
import org.koin.dsl.module

val application = module {
    single { Room.databaseBuilder(get(), TranslatorDataBase::class.java, DATABASE_NAME).fallbackToDestructiveMigration().build() }
    single { get<TranslatorDataBase>().historyDao() }
    single { get<TranslatorDataBase>().favouriteDao() }
    single<IRepository<List<DataModel>>> { RepositoryImpl(RetrofitImpl()) }
    single<IRepositoryLocal<List<DataModel>>> { RepositoryImplLocal(RoomDataBaseImpl(historyDao = get(), favouriteDao = get()))
    }
}

val mainScreen = module {
    factory { MainViewModel(interactor = get()) }
    factory { MainInteractor(remoteRepository = get(), localRepository = get()) }
}

val historyScreen = module {
    factory { com.example.historyscreen.HistoryViewModel(interactor = get()) }
    factory {
        com.example.historyscreen.HistoryInteractor(
            repositoryRemote = get(),
            repositoryLocal = get()
        )
    }
}

val favouriteScreen = module {
    factory { com.example.favouritescreen.FavouriteViewModel(interactor = get()) }
    factory { com.example.favouritescreen.FavouriteInteractor(repositoryLocal = get()) }
}