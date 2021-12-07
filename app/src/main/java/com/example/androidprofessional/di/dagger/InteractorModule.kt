package com.example.androidprofessional.di.dagger

import com.example.androidprofessional.di.NAME_LOCAL
import com.example.androidprofessional.di.NAME_REMOTE
import com.example.androidprofessional.model.data.DataModel
import com.example.androidprofessional.model.repository.IRepository
import com.example.androidprofessional.usecase.MainInteractor
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class InteractorModule {
    @Provides
    internal fun provideInteractor(
            @Named(NAME_REMOTE) repositoryRemote: IRepository<List<DataModel>>,
            @Named(NAME_LOCAL) repositoryLocal: IRepository<List<DataModel>>
    ) = MainInteractor(repositoryRemote, repositoryLocal)
}