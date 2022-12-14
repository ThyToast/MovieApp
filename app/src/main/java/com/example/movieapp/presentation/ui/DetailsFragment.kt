package com.example.movieapp.presentation.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.presentation.adapters.MovieGenreAdapter
import com.example.movieapp.data.MovieSelection.movieImageUrl
import com.example.movieapp.databinding.FragmentDetailsBinding
import com.example.movieapp.presentation.ui.base.BaseFragment
import com.example.movieapp.presentation.viewmodel.DetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : BaseFragment<FragmentDetailsBinding>() {
    private val viewModel: DetailsViewModel by viewModels()
    private val args: DetailsFragmentArgs by navArgs()
    private lateinit var movieAdapter: MovieGenreAdapter

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDetailsBinding
        get() = FragmentDetailsBinding::inflate

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getMovieDetail(args.movieId)

        binding.fabBookMovie.setOnClickListener {
            findNavController().navigate(DetailsFragmentDirections.actionDetailsFragmentToBookMovieFragment())
        }
        binding.rvGenre.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            movieAdapter = MovieGenreAdapter()
            adapter = movieAdapter
            isNestedScrollingEnabled = false
        }
        viewModel.movieDetailRes.observe(viewLifecycleOwner) {
            binding.tvTitle.text = it.title
            binding.tvLanguage.text = it.originalLanguage.uppercase()
            binding.tvDuration.text = it.runtime + " minutes"
            binding.tvSynopsis.text = it.overview

            if (it.posterPath.isNotBlank()) {
                Glide.with(requireContext())
                    .load(movieImageUrl + it.posterPath)
                    .into(binding.ivMoviePoster)
            } else {
                Glide.with(requireContext())
                    .load(R.drawable.ic_launcher_background)
                    .into(binding.ivMoviePoster)
            }

            it.genres.let { genre -> movieAdapter.updateList(genre) }
            movieAdapter.notifyDataSetChanged()
        }
    }
}