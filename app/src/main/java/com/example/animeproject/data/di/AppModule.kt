package com.example.animeproject.data.di

import com.example.animeproject.databinding.FragmentSearchBinding
import com.example.animeproject.domain.ApiService
import com.example.animeproject.domain.instance.RetrofitInstance
import com.example.animeproject.presentation.anime_info.FullAnimeInformationPresenter
import com.example.animeproject.presentation.anime_info.repository.FullAnimeInformationRepository
import com.example.animeproject.presentation.main.MainPresenter
import com.example.animeproject.presentation.main.repository.MainRepository
import com.example.animeproject.presentation.search.SearchFragment
import com.example.animeproject.presentation.search.SearchPresenter
import com.example.animeproject.presentation.search.repository.SearchRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
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
    fun provideFullAnimeInfoRepository(apiService: ApiService): FullAnimeInformationRepository{
        return FullAnimeInformationRepository(apiService)
    }

    @Provides
    @Singleton
    fun provideDatabase(): DatabaseReference{
        return Firebase.database.reference
    }

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth{
        return FirebaseAuth.getInstance()
    }
}