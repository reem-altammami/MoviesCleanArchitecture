package com.reem.moviescleanarchitecture.ui.moviedetails

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reem.moviescleanarchitecture.core.MoviesApiStatus
import com.reem.moviescleanarchitecture.domain.usecase.GetMovieDetailsUseCase
import com.reem.moviescleanarchitecture.ui.moviedetails.uistate.MovieDetailsItemUiState
import com.reem.moviescleanarchitecture.ui.moviedetails.uistate.MovieDetailsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(val getMovieDetailsUseCase: GetMovieDetailsUseCase):ViewModel() {

    private val _movieDetailsUi = MutableStateFlow(MovieDetailsUiState())
    val movieDetailsUi =_movieDetailsUi.asStateFlow()

init {

}
    fun getMovieDetailsById(movieId:Int){
        viewModelScope.launch {
            _movieDetailsUi.update {
                it.copy(
                    status = MoviesApiStatus.LOADING
                )
            }
            try {
                val movie = getMovieDetailsUseCase.invoke(movieId)
                Log.e("mov","${movie.toString()}")
                val movieDetails= movie.let {
                    MovieDetailsItemUiState(
                        title = it.title!!,
                        info = it.overview!!,
                        poster = it.posterPath!!,
                        background = it.backdropPath!!,
                        rate = it.voteAverage!!,
                        date = it.releaseDate!!,
                        runTime = it.runtime!!
                    )
                }

                if (movieDetails.equals(null)){
                    _movieDetailsUi.update {
                        it.copy(
                            status = MoviesApiStatus.EMPTY
                        )
                    }
                } else{
                    _movieDetailsUi.update {
                        it.copy(
                            movieDetailsItem = movieDetails,
                            status = MoviesApiStatus.DONE
                        )
                    }
                }

            } catch (e :Exception){
                Log.e("mo","${e.message}")
                _movieDetailsUi.update {
                    it.copy(
                        status = MoviesApiStatus.ERROR
                    )
                }
            }

        }

    }
}