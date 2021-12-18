package com.example.androidprofessional.di.koin

import androidx.room.Room
import com.example.androidprofessional.di.DATABASE_NAME
import com.example.androidprofessional.ui.MainFragment
import com.example.androidprofessional.usecase.main.MainInteractor
import com.example.androidprofessional.viewmodel.MainViewModel
import com.example.favouritescreen.FavouriteFragment
import com.example.favouritescreen.FavouriteInteractor
import com.example.favouritescreen.FavouriteViewModel
import com.example.historyscreen.HistoryFragment
import com.example.historyscreen.HistoryInteractor
import com.example.historyscreen.HistoryViewModel
import com.example.module.data.DataModel
import com.example.repository.datasource.RoomDataBaseImpl
import com.example.repository.repository.IRepository
import com.example.repository.repository.IRepositoryLocal
import com.example.repository.repository.RepositoryImpl
import com.example.repository.repository.RepositoryImplLocal
import com.example.repository.retrofit.RetrofitImpl
import com.example.repository.room.TranslatorDataBase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val application = module {
    single { Room.databaseBuilder(get(), TranslatorDataBase::class.java, DATABASE_NAME).fallbackToDestructiveMigration().build() }
    single { get<TranslatorDataBase>().historyDao() }
    single { get<TranslatorDataBase>().favouriteDao() }
    single<IRepository<List<DataModel>>> { RepositoryImpl(RetrofitImpl()) }
    single<IRepositoryLocal<List<DataModel>>> {
        RepositoryImplLocal(RoomDataBaseImpl(historyDao = get(), favouriteDao = get()))
    }
}

val mainScreen = module {
    scope<MainFragment> {
        scoped { MainInteractor(remoteRepository = get(), localRepository = get()) }
        viewModel { MainViewModel(interactor = get()) }
    }
}

val historyScreen = module {
    scope<HistoryFragment> {
        scoped {
            HistoryInteractor(
                    repositoryRemote = get(),
                    repositoryLocal = get())
        }
        viewModel { HistoryViewModel(interactor = get()) }
    }
}

val favouriteScreen = module {
    scope<FavouriteFragment> {
        scoped { FavouriteInteractor(repositoryLocal = get()) }
        viewModel { FavouriteViewModel(interactor = get()) }
    }
}