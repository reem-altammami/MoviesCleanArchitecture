package com.reem.moviescleanarchitecture.domain.repository

import com.reem.moviescleanarchitecture.domain.entity.MovieDetails
import com.reem.moviescleanarchitecture.domain.entity.Movies

interface MoviesRepository {
    suspend fun getMovies(): Movies
    suspend fun getMovieDetails(movieId:Int):MovieDetails
}