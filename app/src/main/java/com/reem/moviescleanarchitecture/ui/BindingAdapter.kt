package com.reem.moviescleanarchitecture.ui

import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.reem.moviescleanarchitecture.R
import com.reem.moviescleanarchitecture.ui.movies.MoviesAdapter
import com.reem.moviescleanarchitecture.core.MoviesApiStatus
import com.reem.moviescleanarchitecture.ui.movies.uistate.MoviesItemUiState

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<MoviesItemUiState>?) {
    val adapter = recyclerView.adapter as MoviesAdapter
    adapter.submitList(data)
}

@BindingAdapter("poster")
fun bindImage(imgView: ImageView, imgUrl: String?) {

    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().build()
        Log.e("TAG", "uri:${imgUri}")
        Glide.with(imgView)
            .load("https://image.tmdb.org/t/p/w500${imgUri}")
            .placeholder(R.drawable.loading_animation)
            .error(R.drawable.ic_broken_image)
            .into(imgView)
        Log.e("TAG", "uri:${imgUri}")

    }

}

@BindingAdapter("apiStatus")
fun bindApiStatus(statusImageView: ImageView, status: MoviesApiStatus) {
    when (status) {
        MoviesApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        MoviesApiStatus.ERROR -> {
            statusImageView.visibility=View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_broken_image)
        }
        MoviesApiStatus.EMPTY -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.noresult)
        }
        MoviesApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }

    }
}