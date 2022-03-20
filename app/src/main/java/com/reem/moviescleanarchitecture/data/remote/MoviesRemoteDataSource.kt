package com.reem.moviescleanarchitecture.data.remote

import com.reem.moviescleanarchitecture.data.MoviesDataSource
import com.reem.moviescleanarchitecture.data.network.MoviesApi
import com.reem.moviescleanarchitecture.domain.entity.MovieDetails
import com.reem.moviescleanarchitecture.domain.entity.Movies
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MoviesRemoteDataSource @Inject constructor(val api :MoviesApi, val ioDispatcher: CoroutineDispatcher) : MoviesDataSource {
    override suspend fun getMovies(): Movies =
        withContext(ioDispatcher)
        { api.getMovies() }

    override suspend fun getMoviesDetails(movieId:Int): MovieDetails =
        withContext(ioDispatcher){
            api.getMovieDetails(movieId)
        }

}