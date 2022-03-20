package com.reem.moviescleanarchitecture.ui.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.navigation.R
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.reem.moviescleanarchitecture.databinding.MoviesItemBinding
import androidx.recyclerview.widget.ListAdapter
import com.reem.moviescleanarchitecture.ui.movies.uistate.MoviesItemUiState
import com.reem.moviescleanarchitecture.ui.movies.uistate.MoviesUiState


class MoviesAdapter : ListAdapter<MoviesItemUiState,MoviesAdapter.MoviesViewHolder>(MoviesAdapter.DiffCallback) {


    class MoviesViewHolder(val binding:MoviesItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(moviesItemUiState: MoviesItemUiState){
            binding.movie = moviesItemUiState
        }
        val card :CardView = binding.movieCard
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MoviesAdapter.MoviesViewHolder {
       return MoviesViewHolder(MoviesItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: MoviesAdapter.MoviesViewHolder, position: Int) {
        val movieItem = getItem(position)
        holder.bind(movieItem)
        holder.card.setOnClickListener {
            val action = MoviesListFragmentDirections.actionMoviesListFragmentToMovieDetailsFragment(movieItem.id)
holder.card.findNavController().navigate(action)

        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<MoviesItemUiState>() {
        override fun areItemsTheSame(oldItem: MoviesItemUiState, newItem: MoviesItemUiState): Boolean {
            return   oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: MoviesItemUiState, newItem: MoviesItemUiState): Boolean {
            return   oldItem.title == newItem.title
        }
    }
}