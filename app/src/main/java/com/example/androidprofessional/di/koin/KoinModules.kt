package com.example.androidprofessional.di.koin

import androidx.room.Room
import com.example.androidprofessional.di.NAME_LOCAL
import com.example.androidprofessional.di.NAME_REMOTE
import com.example.androidprofessional.model.data.DataModel
import com.example.androidprofessional.model.repository.*
import com.example.androidprofessional.model.retrofit.RetrofitImpl
import com.example.androidprofessional.model.room.RoomDataBaseImpl
import com.example.androidprofessional.model.room.TranslatorDataBase
import com.example.androidprofessional.usecase.history.HistoryInteractor
import com.example.androidprofessional.usecase.main.MainInteractor
import com.example.androidprofessional.viewmodel.HistoryViewModel
import com.example.androidprofessional.viewmodel.MainViewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val application = module {
//    single<IRepository<List<DataModel>>>(named(NAME_REMOTE)) {
//        RepositoryImpl(RetrofitImpl())
//    }
//
//    single<IRepository<List<DataModel>>>(named(NAME_LOCAL)) {
//        RepositoryImpl(RoomDataBaseImpl())
//    }

    single { Room.databaseBuilder(get(), TranslatorDataBase::class.java, "TranslatorDB").build() }
    single { get<TranslatorDataBase>().historyDao() }
    single<IRepository<List<DataModel>>> { RepositoryImpl(RetrofitImpl()) }
    single<IRepositoryLocal<List<DataModel>>> { RepositoryImplLocal(RoomDataBaseImpl(get()))
    }
}

val mainScreen = module {
    factory { MainViewModel(get()) }
    factory { MainInteractor(get(), get()) }
}

val historyScreen = module {
    factory { HistoryViewModel(get()) }
    factory { HistoryInteractor(get(), get()) }
}