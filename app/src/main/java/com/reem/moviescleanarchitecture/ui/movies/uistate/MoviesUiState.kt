package com.reem.moviescleanarchitecture.ui.movies.uistate

import com.reem.moviescleanarchitecture.core.MoviesApiStatus


data class MoviesUiState (
    val MoviesUiStateList : List<MoviesItemUiState> = listOf(),
    val status : MoviesApiStatus = MoviesApiStatus.EMPTY
        )
data class MoviesItemUiState (
    val id :Int = 0,
    val title :String = "",
    val poster: String =""
        )