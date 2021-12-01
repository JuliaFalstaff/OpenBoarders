package com.example.androidprofessional.di

import com.example.androidprofessional.model.data.DataModel
import com.example.androidprofessional.model.repository.IDataSource
import com.example.androidprofessional.model.repository.IRepository
import com.example.androidprofessional.model.repository.RepositoryImpl
import com.example.androidprofessional.model.retrofit.RetrofitImpl
import com.example.androidprofessional.model.room.RoomDataBaseImplementation
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    @Named(NAME_REMOTE)
    internal fun provideRepositoryRemote(
        @Named(NAME_REMOTE) dataSourceRemote: IDataSource<List<DataModel>>
    ): IRepository<List<DataModel>> = RepositoryImpl(dataSourceRemote)

    @Provides
    @Singleton
    @Named(NAME_LOCAL)
    internal fun provideRepositoryLocal(
        @Named(NAME_LOCAL) dataSourceLocal: IDataSource<List<DataModel>>
    ): IRepository<List<DataModel>> = RepositoryImpl(dataSourceLocal)

    @Provides
    @Singleton
    @Named(NAME_REMOTE)
    internal fun provideDataSourceRemote(): IDataSource<List<DataModel>> = RetrofitImpl()

    @Provides
    @Singleton
    @Named(NAME_LOCAL)
    internal fun provideDataSourceLocal(): IDataSource<List<DataModel>> =
        RoomDataBaseImplementation()
}