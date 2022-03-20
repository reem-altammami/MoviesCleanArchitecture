package com.reem.moviescleanarchitecture.data

import com.reem.moviescleanarchitecture.domain.entity.MovieDetails
import com.reem.moviescleanarchitecture.domain.entity.Movies

interface MoviesDataSource {
    suspend fun getMovies(): Movies
    suspend fun getMoviesDetails(movieId:Int):MovieDetails
}