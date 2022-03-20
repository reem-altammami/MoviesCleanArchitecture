package com.reem.moviescleanarchitecture.data.network

import com.reem.moviescleanarchitecture.domain.entity.MovieDetails
import com.reem.moviescleanarchitecture.domain.entity.Movies
import retrofit2.http.GET
import retrofit2.http.Path

interface MoviesApi {

    @GET("/3/movie/popular?api_key=a06d47a0011d67e6a6c69f40321a686c")
    suspend fun getMovies() : Movies

    @GET("/3/movie/{id}?api_key=a06d47a0011d67e6a6c69f40321a686c")
    suspend fun getMovieDetails(@Path("id")movieId:Int):MovieDetails
}