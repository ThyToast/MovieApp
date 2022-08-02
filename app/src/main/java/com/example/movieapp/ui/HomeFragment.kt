package com.example.movieapp.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.adapters.DiscoverMovieAdapter
import com.example.movieapp.databinding.FragmentHomeBinding
import com.example.movieapp.viewmodel.HomeViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModels()
    private var searchFilter = filterValues[3]

    private lateinit var movieAdapter: DiscoverMovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        viewModel.getDiscoverMovieData(searchFilter)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
            movieAdapter = DiscoverMovieAdapter()
            adapter = movieAdapter
            isNestedScrollingEnabled = false
        }

        binding.swRefreshMovie.setOnRefreshListener {
            viewModel.getDiscoverMovieData(searchFilter)
        }

        viewModel.discoverMovieRes.observe(viewLifecycleOwner) {
            // limits the amount of movies for performance
            val response = it.results?.take(20)
            if (response != null) {
                movieAdapter.updateList(response)
            }
            movieAdapter.notifyDataSetChanged()
            binding.swRefreshMovie.isRefreshing = false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private val movieFilter: Array<String> = arrayOf(
            "By popularity (Ascending)",
            "By popularity (Descending)",
            "By release date (Ascending)",
            "By release date (Descending)",
            "By alphabetical order (Ascending)",
            "By alphabetical order (Descending)",
        )

        private val filterValues: Array<String> = arrayOf(
            "popularity.asc",
            "popularity.desc",
            "release_date.asc",
            "release_date.desc",
            "original_title.asc",
            "original_title.desc",
        )
    }
}