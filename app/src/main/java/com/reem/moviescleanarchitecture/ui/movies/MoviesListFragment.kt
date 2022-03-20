package com.reem.moviescleanarchitecture.ui.movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.reem.moviescleanarchitecture.databinding.FragmentMoviesListBinding
import com.reem.moviescleanarchitecture.ui.bindRecyclerView
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MoviesListFragment : Fragment() {
  private var _binding : FragmentMoviesListBinding? =null
    private val binding get() = _binding!!
private val moviesViewModel : MoviesViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMoviesListBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.moviesViewModel = moviesViewModel
        binding.gridRecyclerView.adapter=MoviesAdapter()

        setHasOptionsMenu(true)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED){
moviesViewModel.moviesUi.collect{
    bindRecyclerView(binding.gridRecyclerView,it.MoviesUiStateList)
}
            }
        }
    }
}