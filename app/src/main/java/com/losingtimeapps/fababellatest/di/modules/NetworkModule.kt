package com.losingtimeapps.fababellatest.di.modules

import com.google.gson.Gson
import com.losingtimeapps.fababellatest.BuildConfig
import com.losingtimeapps.fababellatest.data.remote.IndicatorService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

import javax.inject.Named
import javax.inject.Singleton

@Module
internal class NetworkModule {

    @Provides
    fun provideService(retrofit: Retrofit): IndicatorService = retrofit.create(IndicatorService::class.java)

    @Provides
    @Singleton
    fun provideRetrofitInstance(
        @Named("API_URL") baseUrl: String, gson: Gson, okHttpClient: OkHttpClient
    ) = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()


    @Provides
    @Named("API_URL")
    fun provideBaseUrl(): String {
        return BuildConfig.API_URL
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun provideApiOkHttpClient(
    ): OkHttpClient = OkHttpClient().newBuilder().build()
}
