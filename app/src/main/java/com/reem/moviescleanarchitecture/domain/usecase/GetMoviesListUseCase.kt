package com.reem.moviescleanarchitecture.domain.usecase

import com.reem.moviescleanarchitecture.domain.repository.MoviesRepository
import com.reem.moviescleanarchitecture.domain.entity.Movies
import javax.inject.Inject

class GetMoviesListUseCase @Inject constructor(private val moviesRepository: MoviesRepository) {
    suspend fun invoke(): Movies = moviesRepository.getMovies()
}