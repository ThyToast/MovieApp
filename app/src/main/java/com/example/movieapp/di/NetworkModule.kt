package com.example.movieapp.di

import com.example.movieapp.BuildConfig
import com.example.movieapp.api.MovieService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideMovieApi(): MovieService {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(BuildConfig.API_KEY)
            .build()
            .create(MovieService::class.java)
    }
}