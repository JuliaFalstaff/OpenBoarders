package com.example.androidprofessional.di.koin

import androidx.room.Room
import com.example.androidprofessional.di.DATABASE_NAME
import com.example.androidprofessional.model.data.DataModel
import com.example.androidprofessional.model.repository.*
import com.example.androidprofessional.model.retrofit.RetrofitImpl
import com.example.androidprofessional.model.datasource.RoomDataBaseImpl
import com.example.androidprofessional.model.room.TranslatorDataBase
import com.example.androidprofessional.usecase.FavouriteInteractor
import com.example.androidprofessional.usecase.history.HistoryInteractor
import com.example.androidprofessional.usecase.main.MainInteractor
import com.example.androidprofessional.viewmodel.FavouriteViewModel
import com.example.androidprofessional.viewmodel.HistoryViewModel
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
    factory { HistoryViewModel(interactor = get()) }
    factory { HistoryInteractor(repositoryRemote = get(), repositoryLocal = get()) }
}

val favouriteScreen = module {
    factory { FavouriteViewModel(interactor = get()) }
    factory { FavouriteInteractor(repositoryLocal = get()) }
}