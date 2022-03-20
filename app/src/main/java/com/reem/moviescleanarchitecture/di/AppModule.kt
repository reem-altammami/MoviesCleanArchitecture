package com.reem.moviescleanarchitecture.di

import com.reem.moviescleanarchitecture.data.MoviesDataSource
import com.reem.moviescleanarchitecture.domain.repository.MoviesRepository
import com.reem.moviescleanarchitecture.data.network.MoviesApi
import com.reem.moviescleanarchitecture.data.remote.MoviesRemoteDataSource
import com.reem.moviescleanarchitecture.data.repository.DefaultMoviesRepository
import com.reem.moviescleanarchitecture.domain.usecase.GetMoviesListUseCase
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

private const val BASE_URL = "https://api.themoviedb.org"

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    }

    @Singleton
    @Provides
    fun provideMoviesApi(moshi: Moshi): MoviesApi {
        return Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi)).baseUrl(
            BASE_URL
        ).build().create(MoviesApi::class.java)
    }

    @Singleton
    @Provides
    fun provideDispatcher(): CoroutineDispatcher {
        return Dispatchers.IO
    }

    @Singleton
    @Provides
    fun provideDataSource(api: MoviesApi, dispatcher: CoroutineDispatcher): MoviesDataSource =
        MoviesRemoteDataSource(api, dispatcher)

    @Singleton
    @Provides
    fun provideMoviesRepository(moviesDataSource: MoviesDataSource): MoviesRepository = DefaultMoviesRepository(moviesDataSource)

//    @Singleton
//    @Provides
//    fun provideMoviesUseCase(moviesRepository: MoviesRepository):GetMoviesListUseCase{
//        return GetMoviesListUseCase(moviesRepository)
//    }

}