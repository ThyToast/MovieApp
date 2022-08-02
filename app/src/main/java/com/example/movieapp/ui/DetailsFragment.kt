package com.example.movieapp.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.adapters.MovieGenreAdapter
import com.example.movieapp.databinding.FragmentDetailsBinding
import com.example.movieapp.viewmodel.DetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DetailsViewModel by viewModels()
    private val args: DetailsFragmentArgs by navArgs()

    private lateinit var movieAdapter: MovieGenreAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        viewModel.getMovieDetail(args.movieId)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
                    .load("https://image.tmdb.org/t/p/original" + it.posterPath)
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}