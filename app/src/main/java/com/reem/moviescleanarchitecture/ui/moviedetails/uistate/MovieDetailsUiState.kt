package com.reem.moviescleanarchitecture.ui.moviedetails.uistate

import com.reem.moviescleanarchitecture.core.MoviesApiStatus

data class MovieDetailsUiState (
val movieDetailsItem: MovieDetailsItemUiState = MovieDetailsItemUiState(),
val status: MoviesApiStatus = MoviesApiStatus.EMPTY
        )
data class MovieDetailsItemUiState(
    val title :String ="",
    val info :String="",
    val poster : String="",
    val background :String="",
    val rate : Double= 0.0,
    val date : String="",
    val runTime : Int=0
)