package com.example.animeproject.data.di

import com.example.animeproject.domain.ApiService
import com.example.animeproject.domain.instance.RetrofitInstance
import com.example.animeproject.presentation.main.MainPresenter
import com.example.animeproject.presentation.main.repository.MainRepository
import com.example.animeproject.presentation.search.SearchPresenter
import com.example.animeproject.presentation.search.repository.SearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Provider
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

    @Provides
    @Singleton
    fun provideMainPresenter(mainRepository: MainRepository): MainPresenter{
        return MainPresenter(mainRepository)
    }

    @Provides
    @Singleton
    fun provideSearchRepository(apiService: ApiService): SearchRepository {
        return SearchRepository(apiService)
    }

    @Provides
    @Singleton
    fun provideSearchPresenter(searchRepository: SearchRepository): SearchPresenter{
        return SearchPresenter(searchRepository)
    }

}