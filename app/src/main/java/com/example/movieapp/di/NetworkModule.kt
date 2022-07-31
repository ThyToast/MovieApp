package com.example.movieapp.di

import com.example.movieapp.BuildConfig
import com.example.movieapp.api.MovieService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val BASE_URL = "https://api.themoviedb.org/3/"
    // HTTP interceptor used to debug API calls easily
    // Token interceptor to add key without having to repeat it during API calls

    @Singleton
    @Provides
    fun provideMovieApi(): MovieService {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(TokenInterceptor())
                    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
                    .build()
            )
            .build()
            .create(MovieService::class.java)
    }
}

class TokenInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var original = chain.request()
        val token = BuildConfig.API_KEY
        val url = original.url.newBuilder().addQueryParameter("api_key", token).build()
        original = original.newBuilder().url(url).build()
        return chain.proceed(original)
    }
}