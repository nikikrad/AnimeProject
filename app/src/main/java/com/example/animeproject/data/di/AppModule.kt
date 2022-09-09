package com.example.animeproject.data.di

import com.example.animeproject.domain.ApiService
import com.example.animeproject.domain.instance.RetrofitInstance
import com.example.animeproject.presentation.main.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return RetrofitInstance.getRetrofitInstance().create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideMainRepository(apiService: ApiService): MainRepository {
        return MainRepository(apiService)
    }

}