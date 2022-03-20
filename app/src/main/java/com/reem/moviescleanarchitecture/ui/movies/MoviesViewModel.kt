package com.reem.moviescleanarchitecture.ui.movies

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.reem.moviescleanarchitecture.domain.usecase.GetMoviesListUseCase
import com.reem.moviescleanarchitecture.core.MoviesApiStatus
import com.reem.moviescleanarchitecture.ui.movies.uistate.MoviesItemUiState
import com.reem.moviescleanarchitecture.ui.movies.uistate.MoviesUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(val getMoviesListUseCase: GetMoviesListUseCase) : ViewModel() {

    private val _moviesUi = MutableStateFlow(MoviesUiState())
    val moviesUi : StateFlow<MoviesUiState> = _moviesUi.asStateFlow()

init {
    getMovies()
}

    fun getMovies () {

        viewModelScope.launch {
            _moviesUi.update {
                it.copy(status = MoviesApiStatus.LOADING)
            }
            try {
                val movies = getMoviesListUseCase.invoke()
                Log.e("mo","${movies.results?.get(0)?.id.toString()}")
                val moviesList = movies.results!!.map {
                    MoviesItemUiState(
                        id = it?.id!!,
                        title = it?.title!!,
                        poster = it?.posterPath!!
                    )
                }
                if (moviesList.isEmpty()){
                    _moviesUi.update {
                        it.copy(
                            status = MoviesApiStatus.EMPTY
                        )
                    }
                } else{
                    _moviesUi.update{
                        it.copy(
                            MoviesUiStateList = moviesList,
                            status = MoviesApiStatus.DONE
                        )
                    }
                }

            } catch (e:Exception){
                _moviesUi.update {
                    it.copy(
                        status = MoviesApiStatus.ERROR
                    )
                }
            }


        }

        }
    }

