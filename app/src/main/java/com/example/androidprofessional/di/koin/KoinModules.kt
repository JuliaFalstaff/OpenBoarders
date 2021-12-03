package com.example.androidprofessional.di.koin

import com.example.androidprofessional.di.NAME_LOCAL
import com.example.androidprofessional.di.NAME_REMOTE
import com.example.androidprofessional.model.data.DataModel
import com.example.androidprofessional.model.repository.IRepository
import com.example.androidprofessional.model.repository.RepositoryImpl
import com.example.androidprofessional.model.retrofit.RetrofitImpl
import com.example.androidprofessional.model.room.RoomDataBaseImplementation
import com.example.androidprofessional.usecase.MainInteractor
import com.example.androidprofessional.viewmodel.MainViewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val application = module {
    single<IRepository<List<DataModel>>>(named(NAME_REMOTE)) {
        RepositoryImpl(RetrofitImpl())
    }
    single<IRepository<List<DataModel>>>(named(NAME_LOCAL)) {
        RepositoryImpl(RoomDataBaseImplementation())
    }
}

val mainScreen = module {
    factory {
        MainInteractor(
            remoteRepository = get(named(NAME_REMOTE)),
            localRepository = get(named(NAME_LOCAL))
        )
    }
    factory { MainViewModel(interactor = get()) }
}