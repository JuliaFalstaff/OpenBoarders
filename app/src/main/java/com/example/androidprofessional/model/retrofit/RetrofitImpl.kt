package com.example.androidprofessional.model.retrofit

import com.example.androidprofessional.BuildConfig
import com.example.androidprofessional.model.data.DataModel
import com.example.androidprofessional.model.repository.IDataSource
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitImpl : IDataSource<List<DataModel>> {
    override fun getData(word: String): Observable<List<DataModel>> {
        return api.search(word)
    }

    private val api by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(createOkHttpClient())
            .build()
            .create(ApiService::class.java)
    }

    private fun createOkHttpClient(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            httpClient.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        }
        return httpClient.build()
    }

    companion object {
        private const val BASE_URL = "https://dictionary.skyeng.ru/api/public/v1/"
    }
}