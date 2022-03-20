package com.reem.moviescleanarchitecture.data.repository

import com.reem.moviescleanarchitecture.data.MoviesDataSource
import com.reem.moviescleanarchitecture.domain.entity.MovieDetails
import com.reem.moviescleanarchitecture.domain.repository.MoviesRepository
import com.reem.moviescleanarchitecture.domain.entity.Movies
import javax.inject.Inject

class DefaultMoviesRepository @Inject constructor(val remoteDataSource: MoviesDataSource) :
    MoviesRepository {
    override suspend fun getMovies(): Movies =
        remoteDataSource.getMovies()

    override suspend fun getMovieDetails(movieId: Int): MovieDetails =
        remoteDataSource.getMoviesDetails(movieId)

}