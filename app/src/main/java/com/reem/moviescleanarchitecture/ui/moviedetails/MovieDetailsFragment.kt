package com.reem.moviescleanarchitecture.ui.moviedetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.reem.moviescleanarchitecture.R
import com.reem.moviescleanarchitecture.databinding.FragmentMovieDetailsBinding
import com.reem.moviescleanarchitecture.ui.moviedetails.uistate.MovieDetailsUiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {
private val movieDetailsViewModel : MovieDetailsViewModel by viewModels()
    private var _binding : FragmentMovieDetailsBinding? = null
    private val binding get() = _binding!!
lateinit var movieInfo :MovieDetailsUiState
var movieId = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
movieId = it.getInt("movieId")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMovieDetailsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = movieDetailsViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        movieDetailsViewModel.getMovieDetailsById(movieId)
      lifecycleScope.launch {
          repeatOnLifecycle(Lifecycle.State.RESUMED)
          {
              movieDetailsViewModel.movieDetailsUi.collect {

              }
          }
      }
        (activity as AppCompatActivity?)!!.setSupportActionBar(binding.toolBar)!!
        (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        binding.toolBar.setNavigationOnClickListener() {
            findNavController().navigate(MovieDetailsFragmentDirections.actionMovieDetailsFragmentToMoviesListFragment())
        }
    }


}