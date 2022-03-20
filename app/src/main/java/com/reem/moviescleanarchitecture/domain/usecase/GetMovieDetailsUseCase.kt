package com.reem.moviescleanarchitecture.domain.usecase

import com.reem.moviescleanarchitecture.domain.entity.MovieDetails
import com.reem.moviescleanarchitecture.domain.repository.MoviesRepository
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(val getMoviesRepository: MoviesRepository) {
    suspend fun invoke(movieId:Int) : MovieDetails = getMoviesRepository.getMovieDetails(movieId)
}