package com.example.androidprofessional.di.koin

import androidx.room.Room
import com.example.androidprofessional.di.DATABASE_NAME
import com.example.androidprofessional.ui.favourite.FavouriteFragment
import com.example.androidprofessional.usecase.favourite.FavouriteInteractor
import com.example.androidprofessional.ui.favourite.FavouriteViewModel
import com.example.androidprofessional.usecase.history.HistoryInteractor
import com.example.androidprofessional.ui.history.HistoryViewModel
import com.example.androidprofessional.navigation.AndroidScreens
import com.example.androidprofessional.ui.history.HistoryFragment
import com.example.androidprofessional.ui.main.MainFragment
import com.example.androidprofessional.ui.cards.MemoryCardsFragment
import com.example.androidprofessional.usecase.game.MemoryCardsInteractor
import com.example.androidprofessional.usecase.main.MainInteractor
import com.example.androidprofessional.ui.main.MainViewModel
import com.example.androidprofessional.ui.cards.MemoryCardsViewModel
import com.example.module.data.DataModel
import com.example.repository.datasource.RoomDataBaseImpl
import com.example.repository.repository.IRepository
import com.example.repository.repository.IRepositoryLocal
import com.example.repository.repository.RepositoryImpl
import com.example.repository.repository.RepositoryImplLocal
import com.example.repository.retrofit.RetrofitImpl
import com.example.repository.room.TranslatorDataBase
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val application = module {
    single {
        Room.databaseBuilder(get(), TranslatorDataBase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration().build()
    }
    single { get<TranslatorDataBase>().historyDao() }
    single { get<TranslatorDataBase>().favouriteDao() }
    single<IRepository<List<DataModel>>> { RepositoryImpl(RetrofitImpl()) }
    single<IRepositoryLocal<List<DataModel>>> {
        RepositoryImplLocal(RoomDataBaseImpl(historyDao = get(), favouriteDao = get()))
    }
    single { Cicerone.create() }
    single { get<Cicerone<Router>>().router }
    single { get<Cicerone<Router>>().getNavigatorHolder() }
    single { AndroidScreens() }
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
                repositoryLocal = get()
            )
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

val memoryCardsScreen = module {
    scope<MemoryCardsFragment> {
        scoped { MemoryCardsInteractor(repositoryLocal = get()) }
        viewModel { MemoryCardsViewModel(interactor = get()) }
    }
}