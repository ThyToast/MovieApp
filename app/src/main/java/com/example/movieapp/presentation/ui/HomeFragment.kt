package com.example.movieapp.presentation.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.data.MovieResponse
import com.example.movieapp.data.MovieSelection.filterValues
import com.example.movieapp.data.MovieSelection.movieFilter
import com.example.movieapp.databinding.FragmentHomeBinding
import com.example.movieapp.presentation.adapters.DiscoverMovieAdapter
import com.example.movieapp.presentation.ui.base.BaseFragment
import com.example.movieapp.presentation.viewmodel.HomeViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(), DiscoverMovieAdapter.OnMovieListener {
    private val viewModel: HomeViewModel by viewModels()
    private var searchFilter = filterValues[3]
    private var movieResult: List<MovieResponse.MovieResult> = emptyList()

    private lateinit var movieAdapter: DiscoverMovieAdapter

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getDiscoverMovieData(searchFilter)

        binding.fabFilterMovie.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Filter Movie")
                .setSingleChoiceItems(
                    movieFilter, 0
                ) { dialog, which ->
                    searchFilter = filterValues[which]
                    viewModel.getDiscoverMovieData(searchFilter)
                    dialog.dismiss()
                }
                .setNegativeButton("Cancel") { _, _ -> }
                .show()
        }

        binding.rvMovies.apply {
            layoutManager = LinearLayoutManager(requireContext())
            movieAdapter = DiscoverMovieAdapter(this@HomeFragment)
            adapter = movieAdapter
            isNestedScrollingEnabled = false
        }

        binding.swRefreshMovie.setOnRefreshListener {
            viewModel.getDiscoverMovieData(searchFilter)
        }

        viewModel.discoverMovieRes.observe(viewLifecycleOwner) {
            // limits the amount of movies for performance
            val response = it.results.take(20)
            movieResult = response
            movieAdapter.updateList(response)
            movieAdapter.notifyDataSetChanged()
            binding.swRefreshMovie.isRefreshing = false
        }
    }

    override fun onMovieClick(position: Int) {
        if (movieResult.isNotEmpty()) {
            this.findNavController()
                .navigate(
                    HomeFragmentDirections.actionHomeFragmentToDetailsFragment(movieResult[position].id)
                )
        }
    }
}